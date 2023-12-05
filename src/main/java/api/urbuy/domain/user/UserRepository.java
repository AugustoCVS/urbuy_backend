package api.urbuy.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<listUserData> findAllById(Long id);
    Page<User> findAllByActiveTrue(Pageable pageable);
    UserDetails findByEmail(String email);

}
