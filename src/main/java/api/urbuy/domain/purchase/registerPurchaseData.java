package api.urbuy.domain.purchase;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record registerPurchaseData(
        @NotBlank(message = "O nome do pedido não pode ser vazio")
        String name,
        @NotBlank(message = "A data do pedido não pode ser vazio")
        String date,
        @Min(value = 1, message = "O preço não pode ser menor que 1")
        int price,
        @NotBlank(message = "A categoria do pedido não pode ser vazia")
        String category,
        @Min(value = 1, message = "A quantidade do pedido deve ser pelo menos 1")
        int amount
) {
}
