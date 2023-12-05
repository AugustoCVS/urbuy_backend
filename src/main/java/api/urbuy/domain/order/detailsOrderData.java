package api.urbuy.domain.order;

public record detailsOrderData(String name, String date, String price, String amount) {

    public detailsOrderData(Order order){
        this(
                order.getName(),
                order.getDate(),
                order.getPrice(),
                order.getAmount()
        );
    }
}
