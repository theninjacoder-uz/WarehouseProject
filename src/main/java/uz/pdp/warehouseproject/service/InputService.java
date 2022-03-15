package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.InputReceiveDto;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.repository.InputRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class InputService implements BaseService<InputReceiveDto, Input> {
    private final InputRepository inputRepository;
    private final WarehouseService warehouseService;
    private final CurrencyService currencyService;
    private final SupplierService supplierService;

    @Override
    public ApiResponse add(InputReceiveDto item) {

        Warehouse warehouse = warehouseService.checkById(item.getWarehouseId());
        if (warehouse == null)
            return new ApiResponse(4, "Warehouse not found", null);

        Currency currency = currencyService.checkById(item.getCurrencyId());
        if (currency == null)
            return new ApiResponse(4, "Currency not found", null);

        Supplier supplier = supplierService.checkById(item.getSupplierId());
        if (supplier == null)
            return new ApiResponse(4, "Supplier not found", null);

        Input mappedInput = new Input();
        mappedInput.setCurrency(currency);
        mappedInput.setWarehouse(warehouse);
        mappedInput.setSupplier(supplier);
        mappedInput.setCode("1");
        mappedInput.setFactureNumber("123");

        return new ApiResponse(1, SAVED, mappedInput);
    }

    @Override
    public ApiResponse edit(Integer id, InputReceiveDto item) {

        Input input = checkById(id);
        if(input == null)
            return NOT_FOUND;

        Warehouse warehouse = warehouseService.checkById(item.getWarehouseId());
        if (warehouse == null)
            return new ApiResponse(4, "Warehouse not found", null);

        Currency currency = currencyService.checkById(item.getCurrencyId());
        if (currency == null)
            return new ApiResponse(4, "Currency not found", null);

        Supplier supplier = supplierService.checkById(item.getSupplierId());
        if (supplier == null)
            return new ApiResponse(4, "Supplier not found", null);

        input.setCurrency(currency);
        input.setWarehouse(warehouse);
        input.setSupplier(supplier);
        input.setCode("1");
        input.setFactureNumber("123");
        return new ApiResponse(1, EDIT, inputRepository.save(input));
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All inputs", inputRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {
        Input input = checkById(id);
        if(input == null)
            return NOT_FOUND;
        return new ApiResponse(1, "Input", input);
    }

    @Override
    public Input checkById(Integer id) {
        Optional<Input> optional = inputRepository.findById(id);
        return optional.orElse(null);
    }
}
