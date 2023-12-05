package api.urbuy.controller;

import api.urbuy.domain.user.*;
import api.urbuy.infra.Security.TokenService;
import api.urbuy.infra.Security.dataTokenJwt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid registerUserData data, UriComponentsBuilder uriBuilder){
        var user = new User(data);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        String encryptedConfirmPassword = bCryptPasswordEncoder.encode(user.getConfirm_password());
        user.setPassword(encryptedPassword);
        user.setConfirm_password(encryptedConfirmPassword);

        repository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new detailsUserData(user));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<listUserData>> list(@PageableDefault(size = 20, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(listUserData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<detailsUserData> list(@PathVariable Long id){
        var list = repository.getReferenceById(id);
        return ResponseEntity.ok(new detailsUserData(list));
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid updateUserData data) {
        var user = repository.getReferenceById(id);
        user.updateData(data);

        return ResponseEntity.ok(new detailsUserData(user));
    }


    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity delete(@PathVariable Long id){
        User user = repository.getReferenceById(id);
        user.softDelete();

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthData data){
        var auth_token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(auth_token);

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new dataTokenJwt(tokenJWT));
    }

}
