package api.urbuy.domain.order;

public record listOrderData(Long id, String name, String date, String price, String amount) {

    listOrderData(Order order){
        this(
                order.getId(),
                order.getName(),
                order.getDate(),
                order.getPrice(),
                order.getAmount()
        );
    }
}
