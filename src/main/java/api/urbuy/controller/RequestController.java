package api.urbuy.controller;

import api.urbuy.domain.request.*;
import api.urbuy.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/request")
@SecurityRequirement(name = "bearer-key")
public class RequestController {

    @Autowired
    private UserRepository user_repository;

    @Autowired
    private RequestRepository repository;

    @PostMapping("/register/{userId}")
    @Transactional
    public ResponseEntity register(@PathVariable Long userId, @RequestBody @Valid registerRequestData data, UriComponentsBuilder uriBuilder) {
        var user = user_repository.getReferenceById(userId);
        var request = new Request(data);

        request.setUser(user);
        repository.save(request);

        var uri = uriBuilder.path("/address/{id}").buildAndExpand(request.getId()).toUri();

        return ResponseEntity.created(uri).body(new detailsRequestData(request));
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<detailsRequestData> list(@PathVariable Long userId, @PathVariable Long id){
        var user = user_repository.getReferenceById(userId);
        var address = repository.getReferenceById(id);

        if(address.getUser().getId().equals(userId)){
            var list = repository.getReferenceById(id);

            return ResponseEntity.ok(new detailsRequestData(list));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity update(@PathVariable Long userId, @PathVariable Long id, @RequestBody @Valid updateRequestData data){
        var user = user_repository.getReferenceById(userId);
        var request = repository.getReferenceById(id);

        if(request.getUser().getId().equals(userId)){
            request.updateData(data);
        }

        return ResponseEntity.ok(new detailsRequestData(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Request request = repository.getReferenceById(id);
        request.softDelete();

        return ResponseEntity.noContent().build();
    }

}
