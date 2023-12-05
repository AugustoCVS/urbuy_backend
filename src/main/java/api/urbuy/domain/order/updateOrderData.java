package api.urbuy.domain.order;

public record updateOrderData(
        String name,
        String date,
        String price,
        String amount
) {
}
