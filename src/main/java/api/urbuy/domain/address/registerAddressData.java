package api.urbuy.domain.address;

import jakarta.validation.constraints.NotBlank;

public record registerAddressData(
        @NotBlank(message = "O cep não pode ser vazio")
        String cep,
        @NotBlank(message = "A rua não pode ser vazia")
        String street,
        @NotBlank(message = "O numero não pode ser vazio")
        String number,
        String complement
) {
}
