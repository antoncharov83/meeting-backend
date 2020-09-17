package pro.profsoft.meetnetbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileReport;
import pro.profsoft.meetnetbackend.repository.ProfileReportRepository;
import pro.profsoft.meetnetbackend.repository.ProfileRepository;

@Service
public class ProfileReportService {
    private ProfileReportRepository profileReportRepository;
    private ProfileRepository profileRepository;

    public ProfileReportService(ProfileReportRepository profileReportRepository, ProfileRepository profileRepository) {
        this.profileReportRepository = profileReportRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void save(Long accountId, Long imgId, ProfileReport.TypeReport[] typeReports) {
        Profile profile = profileRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        for (ProfileReport.TypeReport typeReport : typeReports) {
            ProfileReport profileReport = new ProfileReport(profile, imgId, typeReport);
            profileReportRepository.saveAndFlush(profileReport);
        }
    }
}
