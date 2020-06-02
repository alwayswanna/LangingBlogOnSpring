package alwayswanna.blogversion.repository;

import alwayswanna.blogversion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
