package api.urbuy.domain.purchase;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @EntityGraph(attributePaths = {"user", "product"})
    List<Purchase> findAllByUserId(Long userId);


    List<Purchase> findAllByProductId(Long productId);
}
