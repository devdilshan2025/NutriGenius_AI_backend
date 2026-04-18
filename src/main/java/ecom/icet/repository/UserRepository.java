package ecom.icet.repository;

import ecom.icet.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Email එකෙන් යූසර් කෙනෙක්ව හොයාගන්න මේ method එක පාවිච්චි කරනවා
    Optional<User> findByEmail(String email);
}
