package api.urbuy.controller;

import api.urbuy.domain.address.*;
import api.urbuy.domain.order.*;
import api.urbuy.domain.product.ProductRepository;
import api.urbuy.domain.user.UserRepository;
import api.urbuy.domain.user.detailsUserData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "bearer-key")
public class OrderController {

    @Autowired
    private UserRepository user_repository;

    @Autowired
    private ProductRepository product_repository;

    @Autowired
    private OrderRepository repository;

    @PostMapping("/register/{userId}/{productId}")
    @Transactional
    public ResponseEntity<?> registerOrder(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody @Valid registerOrderData data) {

        var user = user_repository.getReferenceById(userId);
        var product = product_repository.getReferenceById(productId);

        Order order = new Order(data);
        order.setUser(user);
        order.setProduct(product);
        repository.save(order);

        return ResponseEntity.ok(new detailsOrderData(order));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getOrdersByProductId(@PathVariable Long productId) {
        List<Order> orders = repository.findAllByProductId(productId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = repository.findAllByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{userId}/{productId}/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Long id) {
        var user = user_repository.getReferenceById(userId);
        var product = product_repository.getReferenceById(productId);
        var order = repository.getReferenceById(id);

        if(order.getUser().getId().equals(userId) && order.getProduct().getId().equals(productId)){
            var list = repository.getReferenceById(id);

            return ResponseEntity.ok(new detailsOrderData(list));
        }


        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<detailsOrderData> list(@PathVariable Long id){
        var list = repository.getReferenceById(id);
        return ResponseEntity.ok(new detailsOrderData(list));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid updateOrderData data){
        var order = repository.getReferenceById(id);

        order.updateData(data);

        return ResponseEntity.ok(new detailsOrderData(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Order order = repository.getReferenceById(id);
        order.softDelete();

        return ResponseEntity.noContent().build();
    }

}
