package api.urbuy.domain.request;

public record listRequestData(Long id, String name, String date, String price, String amount, String quantity) {

    listRequestData(Request request){
        this(
                request.getId(),
                request.getName(),
                request.getDate(),
                request.getPrice(),
                request.getCategory(),
                request.getAmount()
        );
    }

}
