package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.ProfileReport;

@Repository
public interface ProfileReportRepository extends JpaRepository<ProfileReport, Long> {
}
