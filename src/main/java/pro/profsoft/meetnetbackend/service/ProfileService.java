package pro.profsoft.meetnetbackend.service;

import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.exception.NoContentException;
import pro.profsoft.meetnetbackend.mapper.ProfileMapper;
import pro.profsoft.meetnetbackend.model.Content;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileAsap;
import pro.profsoft.meetnetbackend.model.Relationship;
import pro.profsoft.meetnetbackend.model.dto.profile.MeetNetProfileDto;
import pro.profsoft.meetnetbackend.model.dto.profile.MessageAsapDto;
import pro.profsoft.meetnetbackend.repository.ProfileAsapRepository;
import pro.profsoft.meetnetbackend.repository.ProfileRepository;
import pro.profsoft.meetnetbackend.repository.RelationshipRepository;
import pro.profsoft.meetnetbackend.sferaClient.SferaClient;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final int PAGE_SIZE = 4;
    private CompatibilityService compatibilityService;
    private SferaClient sferaClient;
    private ProfileRepository profileRepository;
    private RelationshipRepository relationshipRepository;
    private ProfileAsapRepository profileAsapRepository;
    private ContentRepository contentRepository;
    private ProfileMapper profileMapper;

    public ProfileService(SferaClient sferaClient, ProfileRepository profileRepository, ContentRepository contentRepository,
                          RelationshipRepository relationshipRepository, ProfileMapper profileMapper,
                          ProfileAsapRepository profileAsapRepository, CompatibilityService compatibilityService) {
        this.sferaClient = sferaClient;
        this.profileRepository = profileRepository;
        this.relationshipRepository = relationshipRepository;
        this.profileAsapRepository = profileAsapRepository;
        this.contentRepository = contentRepository;
        this.profileMapper = profileMapper;
        this.compatibilityService = compatibilityService;
    }

    @Transactional
    public Boolean isFirstTime(String token) {
        Profile profile = sferaClient.getCurrentProfile(token);
        ProfileOffset.setOffset(profile.getId(), 0);
        return !profileRepository.findById(profile.getId()).isPresent();
    }

    @Transactional
    public MeetNetProfileDto getCurrent(String token) {
        Profile profile = sferaClient.getCurrentProfile(token);
        profile.setToken(token);
        Profile profileToSave = profileRepository.findById(profile.getId()).orElse(profile);
        Set<Content> content = sferaClient.getImages(profile.getId(), token)
                .stream()
                .map(avatarDto -> avatarDto.getContentDto()
                        .stream()
                        .map(c -> ProfileMapper.sferaContentToMeetnet(c))
                        .findAny()
                        .get())
                .limit(5)
                .collect(Collectors.toSet());
        contentRepository.deleteAllByProfile(profileToSave);
        profileToSave.setContent(null);
        profileRepository.saveAndFlush(profileToSave);
        profileToSave.copy(profile);
        profileToSave.setContent(content);
        profileRepository.save(profileToSave);
        profileToSave.getContent().forEach(c -> {
            c.setProfile(profile);
            contentRepository.saveAndFlush(c);
        });
        return profileMapper
                .profileToMeetNetProfileDto(profileToSave);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MeetNetProfileDto getById(Long id) {
        Profile profile = profileRepository.getByIdWithContent(id)
                .orElseThrow(() -> new EntityNotFoundException(id+" Profile not found"));
        return profileMapper.profileToMeetNetProfileDto(profile);
    }

    @Transactional
    public MeetNetProfileDto dislikeProfile(Long userId, Long anotherUserId) {
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId+" Profile not found"));
        Profile anotherProfile = profileRepository.findById(anotherUserId)
                .orElseThrow(() -> new EntityNotFoundException(anotherUserId+" Profile not found"));
        Relationship relationship = relationshipRepository.findFirstByOwnerProfileAndDislikeProfile(profile, anotherProfile)
                .orElse(new Relationship(profile, anotherProfile));
        relationship.setDateDislike(DateTime.now());
        relationshipRepository.saveAndFlush(relationship);
        return getNext(userId);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Collection<MeetNetProfileDto> getPaginated(Long id, int page) {
        List<Profile> profiles = profileRepository
                .getNextPaginated(id, new DateTime().minusMonths(1), PageRequest.of(page, PAGE_SIZE));
        ProfileOffset.setOffset(id, ProfileOffset.getOffset(id) + profiles.size());
        return profiles.stream().map(p -> {
            MeetNetProfileDto m = profileMapper.profileToMeetNetProfileDto(p);
            m.setCompability(compatibilityService.formCompatibilityAll(id, p.getId()));
            m.setCoincidence(compatibilityService.calculateCompability(id, p.getId()) > 50);
            return m;
        })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MeetNetProfileDto getNext(Long id) {
        Integer offset = ProfileOffset.getOffset(id);
        List<Profile> profiles = profileRepository
                .getNextPaginated(id, new DateTime().minusMonths(1), PageRequest.of(offset, 1));
        if (profiles.size() > 0) {
            ProfileOffset.setOffset(id, ++offset);
            MeetNetProfileDto m = profileMapper.profileToMeetNetProfileDto(profiles.get(0));
            m.setCompability(compatibilityService.formCompatibilityAll(id, m.getId()));
            m.setCoincidence(compatibilityService.calculateCompability(id, m.getId()) > 50);
            return m;
        } else {
            throw new NoContentException("No more profiles");
        }
    }

    @Transactional
    public MessageAsapDto canSendMessageASAP(Long senderId, Long targetId) {
        Profile sender = profileRepository.findById(senderId)
                .orElseThrow(() -> new EntityNotFoundException(senderId+" Profile not found"));
        Profile target = profileRepository.findById(targetId)
                .orElseThrow(() -> new EntityNotFoundException(targetId+" Profile not found"));
        DateTime startDate = DateTime.now().withTimeAtStartOfDay();

        if (profileAsapRepository.countAsapMessageToday(sender, target, startDate, startDate.plusDays(1)) < 5) {
            profileAsapRepository.save(new ProfileAsap(sender, target));
            return new MessageAsapDto(true);
        } else {
            return new MessageAsapDto(false);
        }
    }
}
