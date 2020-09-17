package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Compatibility;

@Repository
public interface CompatibilityRepository extends JpaRepository<Compatibility, Long> {
}
