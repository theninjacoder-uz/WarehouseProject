package uz.pdp.warehouseproject.service.base;

import uz.pdp.warehouseproject.response.ApiResponse;

public interface BaseService <T, R>{

    ApiResponse add(T item);
    ApiResponse edit(Integer id, T item);
    ApiResponse delete(Integer id);
    ApiResponse getAll();
    ApiResponse getById(Integer id);
    R checkById(Integer id);
}
