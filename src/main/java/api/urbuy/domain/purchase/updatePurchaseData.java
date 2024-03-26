package api.urbuy.domain.purchase;

public record updatePurchaseData(
        String name,
        String date,
        String price,
        String amount
) {
}
