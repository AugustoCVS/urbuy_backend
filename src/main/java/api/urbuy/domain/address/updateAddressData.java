package api.urbuy.domain.address;

import jakarta.validation.constraints.NotBlank;

public record updateAddressData(
        String cep,
        String street,
        String number,
        String complement
) {
}
