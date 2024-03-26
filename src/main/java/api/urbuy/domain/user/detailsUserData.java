package api.urbuy.domain.user;

public record detailsUserData(Long id, String name, String email, String cnpj) {

    public detailsUserData(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getCnpj());
    }

}
