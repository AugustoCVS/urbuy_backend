package api.urbuy.domain.purchase;

public record detailsPurchaseData(Long id, String name, String date, int price, int amount, String category) {

    public detailsPurchaseData(Purchase purchase){
        this(
                purchase.getId(),
                purchase.getName(),
                purchase.getDate(),
                purchase.getPrice(),
                purchase.getAmount(),
                purchase.getCategory()
        );
    }
}
