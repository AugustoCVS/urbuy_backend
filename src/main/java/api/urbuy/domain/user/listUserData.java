package api.urbuy.domain.user;

public record listUserData(Long id, String name, String email) {

    public listUserData(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }

}
