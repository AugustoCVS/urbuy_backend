package api.urbuy.controller;

import api.urbuy.domain.product.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid registerProductsData data){
        var prodcut = new Product(data);
        repository.save(prodcut);

        return ResponseEntity.ok(new detailsProductData(prodcut));
    }

    @GetMapping
    public ResponseEntity<Page<listProductData>> list(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(size = 20) Pageable pageable) {

        Page<Product> page;
        if (category != null && !category.isEmpty() && name != null && !name.isEmpty()) {
            page = repository.findAllByActiveTrueAndCategoryAndNameContainingIgnoreCaseAndAmountGreaterThan(category, name, 0, pageable);
        } else if (category != null && !category.isEmpty()) {
            page = repository.findAllByActiveTrueAndCategoryAndAmountGreaterThan(category, 0, pageable);
        } else if (name != null && !name.isEmpty()) {
            page = repository.findAllByActiveTrueAndNameContainingIgnoreCaseAndAmountGreaterThan(name, 0, pageable);
        } else {
            page = repository.findAllByActiveTrueAndAmountGreaterThan(0, pageable);
        }

        return ResponseEntity.ok(page.map(listProductData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<detailsProductData> list(@PathVariable Long id){
        var list = repository.getReferenceById(id);
        return ResponseEntity.ok(new detailsProductData(list));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid updateProductData data) {
        var prodcut = repository.getReferenceById(id);
        prodcut.updateData(data);

        return ResponseEntity.ok(new detailsProductData(prodcut));
    }


    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity delete(@PathVariable Long id){
        Product product = repository.getReferenceById(id);
        product.softDelete();

        return ResponseEntity.noContent().build();
    }

}
