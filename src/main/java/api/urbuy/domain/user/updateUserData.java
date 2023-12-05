package api.urbuy.domain.user;

public record updateUserData(
        String name,
        String email,
        String cnpj
    ) {
}
