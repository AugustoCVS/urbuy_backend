package api.urbuy.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<listAddressData> findAllByUserId(Long id);
}
