package api.urbuy.domain.request;

import jakarta.validation.constraints.NotBlank;

public record registerRequestData(
        @NotBlank(message = "O nome do pedido não pode ser vazio")
        String name,
        @NotBlank(message = "A data do pedido não pode ser vazio")
        String date,
        @NotBlank(message = "O preço total do pedido não pode ser vazio")
        String price,
        @NotBlank(message = "A categoria do pedido não pode ser vazio")
        String category,
        @NotBlank(message = "A quantidade do pedido não pode ser vazio")
        String amount
) {

}
