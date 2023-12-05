package api.urbuy.controller;

import api.urbuy.domain.address.*;
import api.urbuy.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/address")
@SecurityRequirement(name = "bearer-key")
public class AddressController {

    @Autowired
    private UserRepository user_repository;

    @Autowired
    private AddressRepository repository;

    @PostMapping("/register/{userId}")
    @Transactional
    public ResponseEntity register(@PathVariable Long userId, @RequestBody @Valid registerAddressData data, UriComponentsBuilder uriBuilder) {
        var user = user_repository.getReferenceById(userId);
        var address = new Address(data);

        address.setUser(user);
        repository.save(address);

        var uri = uriBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();

        return ResponseEntity.created(uri).body(new detailsAddressData(address));
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<detailsAddressData> list(@PathVariable Long userId, @PathVariable Long id){
        var user = user_repository.getReferenceById(userId);
        var address = repository.getReferenceById(id);

        if(address.getUser().getId().equals(userId)){
            var list = repository.getReferenceById(id);

            return ResponseEntity.ok(new detailsAddressData(list));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity update(@PathVariable Long userId, @PathVariable Long id, @RequestBody @Valid updateAddressData data){
        var user = user_repository.getReferenceById(userId);
        var address = repository.getReferenceById(id);

        if(address.getUser().getId().equals(userId)){
            address.updateData(data);
        }

        return ResponseEntity.ok(new detailsAddressData(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Address address = repository.getReferenceById(id);
            address.softDelete();

        return ResponseEntity.noContent().build();
    }

}
