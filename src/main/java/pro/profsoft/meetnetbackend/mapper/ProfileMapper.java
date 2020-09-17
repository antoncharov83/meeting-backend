package pro.profsoft.meetnetbackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;
import pro.profsoft.meetnetbackend.model.Content;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.dto.profile.MeetNetProfileDto;
import pro.profsoft.meetnetbackend.model.dto.sfera.*;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "content", source = "avatarDto.contentDto", qualifiedByName = "sferaContentToMeetnet")
    Profile profileDtoToProfile(ProfileDto profileDto);
    @Mappings({
            @Mapping(target = "accountId", source = "id", qualifiedByName = "longToString"),
            @Mapping(target = "avatarDto.contentDto", source = "content"),
            @Mapping(target = "avatarDto.socialDto.likes", source = "likes"),
            @Mapping(target = "avatarDto.socialDto.forwards", source = "forwards"),
            @Mapping(target = "avatarDto.socialDto.feedId", source = "feedId")
    })
    MeetNetProfileDto profileToMeetNetProfileDto(Profile profile);

    @Named("sferaContentToMeetnet")
    static Content sferaContentToMeetnet(ContentDto contentDto) {
        try {
        Content content = new Content();
        content.setId(contentDto.getId().longValue());
        content.setType(contentDto.getType());
        String jsonImg = contentDto.getData().replace("\\", "");
        ObjectMapper mapper = new ObjectMapper();
        DataDto dataDto = mapper.readValue(jsonImg, DataDto.class);
        content.setData(dataDto.getUrl());
        return content;
        } catch (JsonProcessingException e) {
            return new Content();
        }
    }

    @Named("longToString")
    static String longToString(Long id) {
        return id.toString();
    }

    @AfterMapping
    default void setFieldsProfile(@MappingTarget ProfileDto profileDto, Profile profile) {
        if (profileDto.getAvatarDto() != null && profileDto.getAvatarDto().getSocialDto() != null) {
            profile.setForwards(profileDto.getAvatarDto().getSocialDto().getForwards());
            profile.setFeedId(profileDto.getAvatarDto().getSocialDto().getFeedId());
            profile.setLikes(profileDto.getAvatarDto().getSocialDto().getLikes());
        }
    }

    @AfterMapping
    default void setFieldsMeetNetProfileDto(@MappingTarget MeetNetProfileDto meetNetProfileDto, Profile profile) {
        if (profile.getForwards() != null || profile.getFeedId() != null
                || profile.getLikes() != null) {
            meetNetProfileDto.setAvatarDto(new AvatarDto());
            meetNetProfileDto.getAvatarDto().setSocialDto(new SocialDto());
            meetNetProfileDto.getAvatarDto().getSocialDto().setForwards(profile.getForwards());
            meetNetProfileDto.getAvatarDto().getSocialDto().setFeedId(profile.getFeedId());
            meetNetProfileDto.getAvatarDto().getSocialDto().setLikes(profile.getLikes());
        }
        // just for test
//        meetNetProfileDto.getTempImg().add("https://imageup.ru/img211/3644060/1261449-blue-and-yellow-macaw.jpg");
//        meetNetProfileDto.getTempImg().add("https://imageup.ru/img211/3644060/1261449-blue-and-yellow-macaw.jpg");
//        meetNetProfileDto.getTempImg().add("https://imageup.ru/img211/3644060/1261449-blue-and-yellow-macaw.jpg");
//        meetNetProfileDto.getTempImg().add("https://imageup.ru/img211/3644060/1261449-blue-and-yellow-macaw.jpg");
    }
}
