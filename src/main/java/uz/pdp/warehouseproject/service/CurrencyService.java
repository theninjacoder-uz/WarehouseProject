package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.repository.CurrencyRepository;
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
public class CurrencyService implements BaseService<Currency, Currency> {
    private final CurrencyRepository currencyRepository;

    @Override
    public ApiResponse add(Currency item) {
        boolean exists = currencyRepository.existsByNameAndActive(item.getName(), true);
        if (exists)
            return new ApiResponse(4, "Currency already exists", null);
        return new ApiResponse(1, SAVED, currencyRepository.save(item));
    }

    @Override
    public ApiResponse edit(Integer id, Currency item) {
        Currency currency = checkById(id);
        if (currency == null)
            return NOT_FOUND;
        currency.setName(item.getName());
        return new ApiResponse(1, EDIT,currencyRepository.save(currency));
    }

    @Override
    public ApiResponse delete(Integer id) {
        Currency currency = checkById(id);
        if (currency == null)
            return NOT_FOUND;
        currency.setActive(false);
        currencyRepository.save(currency);
        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        List<Currency> all = currencyRepository.findAll();
        return new ApiResponse(1, "All currencies", all);
    }

    @Override
    public ApiResponse getById(Integer id) {

        Currency currency = checkById(id);
        if (currency == null)
            return NOT_FOUND;

        return new ApiResponse(1, "Currency", currency);
    }

    @Override
    public Currency checkById(Integer id) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isPresent() && optional.get().isActive())
            return optional.get();
        return null;
    }
}
