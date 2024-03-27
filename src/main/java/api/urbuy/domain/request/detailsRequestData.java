package api.urbuy.domain.request;

public record detailsRequestData(String name, String date, String price, String amount, String quantity) {

    public detailsRequestData(Request request){
        this(
                request.getName(),
                request.getDate(),
                request.getPrice(),
                request.getCategory(),
                request.getAmount()
        );
    }
}
