package api.urbuy.domain.purchase;

public record updatePurchaseData(
        String name,
        String date,
        int price,
        int amount,
        String category
) {
}
