package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.repository.SupplierRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.DELETED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SupplierService implements BaseService<Supplier, Supplier> {

    private final SupplierRepository supplierRepository;
    @Override
    public ApiResponse add(Supplier item) {
        boolean exists = supplierRepository.existsByPhoneNumber(item.getPhoneNumber());
        if(exists)
            return new ApiResponse(2, "Supplier already exists", item);

        return new ApiResponse(1, SAVED,  supplierRepository.save(item));
    }

    @Override
    public ApiResponse edit(Integer id, Supplier item) {

        Supplier supplier = checkById(id);
        if(supplier == null)
            return NOT_FOUND;

        boolean exists = supplierRepository.existsByPhoneNumber(item.getPhoneNumber());
        if(exists)
            return new ApiResponse(2, "Supplier with this number    already exists", item);

        supplier.setPhoneNumber(item.getPhoneNumber());
        supplier.setName(item.getName());

        return new ApiResponse(1, SAVED, supplierRepository.save(supplier));
    }

    @Override
    public ApiResponse delete(Integer id) {
        Supplier supplier = checkById(id);
        if(supplier == null)
            return NOT_FOUND;

        supplier.setActive(false);
        supplierRepository.save(supplier);

        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        List<Supplier> all = supplierRepository.findAll();
        return new ApiResponse(1, "All suppliers", all);
    }

    @Override
    public ApiResponse getById(Integer id) {
        Supplier supplier = checkById(id);
        if(supplier == null)
            return NOT_FOUND;

        return new ApiResponse(1, "Supplier", supplier);
    }

    @Override
    public Supplier checkById(Integer id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if(optional.isPresent() && optional.get().isActive())
            return optional.get();

        return null;
    }
}
