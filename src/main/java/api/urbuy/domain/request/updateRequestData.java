package api.urbuy.domain.request;

public record updateRequestData(
        String name,
        String date,
        String price,
        String amount,
        String category
) {
}
