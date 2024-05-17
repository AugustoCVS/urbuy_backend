package api.urbuy.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public record registerProductsData(
        @NotBlank(message = "O nome do produto não pode ser vazio")
        String name,
        @NotBlank(message = "A descrção do produto não pode ser vazio")
        String description,
        @NotBlank(message = "O preço do produto não pode ser vazio")
        String price,
        @Min(value = 1, message = "A quantidade do pedido deve ser pelo menos 1")
        int amount,
        @NotBlank(message = "A imagem não pode ser vazia")
        String img,
        @NotBlank(message = "A marcar não pode ser vazia")
        String brand,
        @NotBlank(message = "A categoria não pode ser vazia")
        String category
) {
}
