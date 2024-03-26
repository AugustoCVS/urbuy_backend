package api.urbuy.domain.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUserId(Long id);
    List<Purchase> findAllByProductId(Long id);
}
