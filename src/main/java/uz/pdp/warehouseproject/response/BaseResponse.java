package uz.pdp.warehouseproject.response;

public abstract class BaseResponse extends ApiResponse{
    public static  ApiResponse NOT_FOUND = new ApiResponse(4, "DATA NOT FOUND", null);
    public static  ApiResponse DELETED = new ApiResponse(3, "DATA DELETED", null);

}
