package in.projects.moneymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.projects.moneymanager.entity.ProfileEntity;
import java.util.Optional;



public interface ProfileRepository extends JpaRepository<ProfileEntity,Long>{
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity>findByActivationToken(String activationToken);
}
