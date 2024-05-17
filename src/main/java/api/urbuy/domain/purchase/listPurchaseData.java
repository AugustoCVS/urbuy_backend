package api.urbuy.domain.purchase;

public record listPurchaseData(Long id, String name, String date, String price, int amount) {

    listPurchaseData(Purchase purchase){
        this(
                purchase.getId(),
                purchase.getName(),
                purchase.getDate(),
                purchase.getPrice(),
                purchase.getAmount()
        );
    }
}
