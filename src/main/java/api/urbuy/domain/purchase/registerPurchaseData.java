package api.urbuy.domain.purchase;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record registerPurchaseData(
        @NotBlank(message = "O nome do pedido não pode ser vazio")
        String name,
        @NotBlank(message = "A data do pedido não pode ser vazio")
        String date,
        @NotBlank(message = "O preço total do pedido não pode ser vazio")
        String price,
        @Min(value = 1, message = "A quantidade do pedido deve ser pelo menos 1")
        int amount
) {
}
