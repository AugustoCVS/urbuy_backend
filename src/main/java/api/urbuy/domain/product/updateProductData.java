package api.urbuy.domain.product;

public record updateProductData(
        String name,
        String description,
        String price,
        String amount
) {
}
