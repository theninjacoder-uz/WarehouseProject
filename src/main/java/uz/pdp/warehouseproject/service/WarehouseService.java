package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.repository.WarehouseRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.DELETED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WarehouseService implements BaseService<Warehouse, Warehouse> {

    private final WarehouseRepository warehouseRepository;

    @Override
    public ApiResponse add(Warehouse item) {
        boolean exists = warehouseRepository.existsByNameAndActive(item.getName(), true);
        if (exists)
            return new ApiResponse(4, "Warehouse already exists", null);
        return new ApiResponse(1, SAVED, warehouseRepository.save(item));
    }

    @Override
    public ApiResponse edit(Integer id, Warehouse item) {
        Warehouse warehouse = checkById(id);
        if (warehouse == null)
            return NOT_FOUND;
        warehouse.setName(item.getName());
        return new ApiResponse(1, EDIT,warehouseRepository.save(warehouse));
    }

    @Override
    public ApiResponse delete(Integer id) {
        Warehouse warehouse = checkById(id);
        if (warehouse == null)
            return NOT_FOUND;
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        List<Warehouse> all = warehouseRepository.findAll();
        return new ApiResponse(1, "All currencies", all);
    }

    @Override
    public ApiResponse getById(Integer id) {
        Warehouse warehouse = checkById(id);
        if (warehouse == null)
            return NOT_FOUND;

        return new ApiResponse(1, "Warehouse", warehouse);
    }

    @Override
    public Warehouse checkById(Integer id) {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isPresent() && optional.get().isActive())
            return optional.get();
        return null;
    }
}
