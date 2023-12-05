package api.urbuy.domain.order;

import jakarta.validation.constraints.NotBlank;

public record registerOrderData(
        @NotBlank(message = "O nome do pedido não pode ser vazio")
        String name,
        @NotBlank(message = "A data do pedido não pode ser vazio")
        String date,
        @NotBlank(message = "O preço total do pedido não pode ser vazio")
        String price,
        @NotBlank(message = "A quantidade do pedido não pode ser vazio")
        String amount
) {
}
