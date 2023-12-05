package api.urbuy.domain.address;

public record listAddressData(Long id, String cep, String street, String number, String complement ) {

    public listAddressData(Address address){
        this(
                address.getId(),
                address.getCep(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement()
        );
    }
}
