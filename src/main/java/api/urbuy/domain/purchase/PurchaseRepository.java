package api.urbuy.domain.purchase;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @EntityGraph(attributePaths = {"user", "product"})
    List<Purchase> findAllByUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "product"})
    List<Purchase> findAllByUserIdAndNameContainingIgnoreCase(Long userId, String name);

    @EntityGraph(attributePaths = {"user", "product"})
    List<Purchase> findAllByUserIdAndPrice(Long userId, int price);

    @EntityGraph(attributePaths = {"user", "product"})
    List<Purchase> findAllByUserIdAndNameContainingIgnoreCaseAndPrice(Long userId, String name, int price);

    List<Purchase> findAllByProductId(Long productId);
}
