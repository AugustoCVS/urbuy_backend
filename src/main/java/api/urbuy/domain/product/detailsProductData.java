package api.urbuy.domain.product;

public record detailsProductData(String name, String description, String price, String amount, String img, String brand, String category) {

    public detailsProductData(Product product){
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAmount(),
                product.getImg(),
                product.getBrand(),
                product.getCategory()
        );
    }
}
