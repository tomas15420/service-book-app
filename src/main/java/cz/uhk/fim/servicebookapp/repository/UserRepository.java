package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username LIKE '%username' OR u.email LIKE '%username'")
    Optional<User> getUserByUsernameOrEmail(String username);
}
