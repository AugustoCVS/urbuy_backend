package api.urbuy.controller;

import api.urbuy.domain.purchase.*;
import api.urbuy.domain.product.ProductRepository;
import api.urbuy.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchase")
@SecurityRequirement(name = "bearer-key")
public class PurchaseController {

    @Autowired
    private UserRepository user_repository;

    @Autowired
    private ProductRepository product_repository;

    @Autowired
    private PurchaseRepository repository;

    @PostMapping("/register/{userId}/{productId}")
    @Transactional
    public ResponseEntity<?> registerPurchase(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody @Valid registerPurchaseData data) {

        var user = user_repository.getReferenceById(userId);
        var product = product_repository.getReferenceById(productId);

        int purchaseQuantity = data.amount();
        try {
            product.decreaseQuantity(purchaseQuantity);
            product_repository.save(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        Purchase purchase = new Purchase(data);
        purchase.setUser(user);
        purchase.setProduct(product);
        repository.save(purchase);

        return ResponseEntity.ok(new detailsPurchaseData(purchase));
    }

    @GetMapping("/list/product/{productId}")
    public ResponseEntity<?> getPurchasesByProductId(@PathVariable Long productId) {
        List<Purchase> purchases = repository.findAllByProductId(productId);
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/list/user/{userId}")
    public ResponseEntity<?> getPurchasesByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price) {

        List<Purchase> purchases;

        if (name != null && price != null) {
            purchases = repository.findAllByUserIdAndNameContainingIgnoreCaseAndPrice(userId, name, price);
        } else if (name != null) {
            purchases = repository.findAllByUserIdAndNameContainingIgnoreCase(userId, name);
        } else if (price != null) {
            purchases = repository.findAllByUserIdAndPrice(userId, price);
        } else {
            purchases = repository.findAllByUserId(userId);
        }

        List<detailsPurchaseData> detailsList = purchases.stream()
                .map(detailsPurchaseData::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(detailsList);
    }


    @GetMapping("/list/{userId}/{productId}/{id}")
    public ResponseEntity<?> getPurchaseByUserId(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Long id) {
        var user = user_repository.getReferenceById(userId);
        var product = product_repository.getReferenceById(productId);
        var order = repository.getReferenceById(id);

        if(order.getUser().getId().equals(userId) && order.getProduct().getId().equals(productId)){
            var list = repository.getReferenceById(id);

            return ResponseEntity.ok(new detailsPurchaseData(list));
        }


        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<detailsPurchaseData> list(@PathVariable Long id){
        var list = repository.getReferenceById(id);
        return ResponseEntity.ok(new detailsPurchaseData(list));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid updatePurchaseData data){
        var order = repository.getReferenceById(id);

        order.updateData(data);

        return ResponseEntity.ok(new detailsPurchaseData(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Purchase purchase = repository.getReferenceById(id);
        purchase.softDelete();

        return ResponseEntity.noContent().build();
    }

}
