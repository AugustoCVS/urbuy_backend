package api.urbuy.domain.address;

public record detailsAddressData(String cep, String street, String number, String complement ) {

    public detailsAddressData(Address address){
        this(
                address.getCep(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement()
        );
    }
}
