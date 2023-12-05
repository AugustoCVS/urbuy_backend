package api.urbuy.domain.product;

public record detailsProductData(String name, String description, String price, String amount) {

    public detailsProductData(Product product){
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAmount()
        );
    }
}
