package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.OutputReceiveDto;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.repository.OutputRepository;
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
public class OutputService implements BaseService<OutputReceiveDto, Output> {

    private final OutputRepository outputRepository;
    private final CurrencyService currencyService;
    private final WarehouseService warehouseService;

    @Override
    public ApiResponse add(OutputReceiveDto item) {

        Currency currency = currencyService.checkById(item.getCurrencyId());
        if (currency == null)
            return new ApiResponse(4, "Currency not found", null);

        Warehouse warehouse = warehouseService.checkById(item.getWarehouseId());
        if (warehouse == null)
            return new ApiResponse(4, "Warehouse not found", null);

        Output output = new Output();
        output.setCode(item.getCode());
        output.setCurrency(currency);
        output.setWarehouse(warehouse);
        output.setClient(item.getClient());
        output.setFactureNumber(item.getFactureNumber());

        return new ApiResponse(1, SAVED, outputRepository.save(output));
    }

    @Override
    public ApiResponse edit(Integer id, OutputReceiveDto item) {

        Output output = checkById(id);

        if (output == null)
            return new ApiResponse(4, "Output not found", null);

        Currency currency = currencyService.checkById(item.getCurrencyId());
        if (currency == null)
            return new ApiResponse(4, "Currency not found", null);

        Warehouse warehouse = warehouseService.checkById(item.getWarehouseId());
        if (warehouse == null)
            return new ApiResponse(4, "Warehouse not found", null);

        output.setCode(item.getCode());
        output.setCurrency(currency);
        output.setWarehouse(warehouse);
        output.setClient(item.getClient());
        output.setFactureNumber(item.getFactureNumber());

        return new ApiResponse(1, EDIT, outputRepository.save(output));
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All outputs", outputRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {
        Output output = checkById(id);
        if(output == null)
            return NOT_FOUND;
        return new ApiResponse(1, "Output", output);
    }

    @Override
    public Output checkById(Integer id) {
        Optional<Output> optional = outputRepository.findById(id);
        return optional.orElse(null);
    }
}
