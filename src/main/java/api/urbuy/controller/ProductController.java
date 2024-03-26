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
    public ResponseEntity<Page<listProductData>> list(@PageableDefault(size = 20, sort = {"category"}) Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(listProductData::new);
        return ResponseEntity.ok(page);
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
