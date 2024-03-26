package api.urbuy.domain.purchase;

public record detailsPurchaseData(String name, String date, String price, String amount) {

    public detailsPurchaseData(Purchase purchase){
        this(
                purchase.getName(),
                purchase.getDate(),
                purchase.getPrice(),
                purchase.getAmount()
        );
    }
}
