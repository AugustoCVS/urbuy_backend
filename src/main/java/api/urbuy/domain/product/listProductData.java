package api.urbuy.domain.product;

public record listProductData(Long id, String name, String description, String price, String amount, String img, String brand, String category) {

    public listProductData(Product product){
        this(
                product.getId(),
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
