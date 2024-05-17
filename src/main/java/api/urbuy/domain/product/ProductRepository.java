package api.urbuy.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByActiveTrueAndAmountGreaterThan(int amount, Pageable pageable);

    Page<Product> findAllByActiveTrueAndCategoryAndAmountGreaterThan(String category, int amount, Pageable pageable);

    Page<Product> findAllByActiveTrueAndNameContainingIgnoreCaseAndAmountGreaterThan(String name, int amount, Pageable pageable);

    Page<Product> findAllByActiveTrueAndCategoryAndNameContainingIgnoreCaseAndAmountGreaterThan(String category, String name, int amount, Pageable pageable);
}
