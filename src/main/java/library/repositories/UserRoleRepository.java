package library.repositories; // The package where this repository is located at

// Including the needed imports
import org.springframework.data.jpa.repository.JpaRepository;

import library.beans.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}