package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.InputProductReceiveDto;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.InputProduct;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.repository.InputProductRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class InputProductService implements BaseService<InputProductReceiveDto, InputProduct> {
    private final InputProductRepository inputProductRepository;
    private final InputService inputService;
    private final ProductService productService;

    @Override
    public ApiResponse add(InputProductReceiveDto item) {
        Input input = inputService.checkById(item.getInputId());
        if(input == null)
            return new ApiResponse(4, "Input not found", null);

        //todo add product amount to total amount
        Product product = productService.checkById(item.getProductId());
        if(product == null)
            return new ApiResponse(4, "Product not found", null);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setInput(input);
        inputProduct.setProduct(product);
        inputProduct.setAmount(item.getAmount());
        inputProduct.setPrice(item.getPrice());
        inputProduct.setExpireDate(item.getExpireDate());

        return new ApiResponse(1, SAVED, inputProductRepository.save(inputProduct));
    }

    @Override
    public ApiResponse edit(Integer id, InputProductReceiveDto item) {

        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        if(inputProductOptional.isEmpty())
            return NOT_FOUND;

        Input input = inputService.checkById(item.getInputId());
        if(input == null)
            return new ApiResponse(4, "Input not found", null);

        //todo add product amount to total amount
        Product product = productService.checkById(item.getProductId());
        if(product == null)
            return new ApiResponse(4, "Product not found", null);
        InputProduct inputProduct = inputProductOptional.get();

        inputProduct.setInput(input);
        inputProduct.setProduct(product);
        inputProduct.setAmount(item.getAmount());
        inputProduct.setPrice(item.getPrice());
        inputProduct.setExpireDate(item.getExpireDate());

        return new ApiResponse(1, EDIT, inputProduct);
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All input products", inputProductRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {
        InputProduct InputProduct = checkById(id);
        if(InputProduct == null)
            return NOT_FOUND;
        return new ApiResponse(1, "Input Product", InputProduct);
    }

    @Override
    public InputProduct checkById(Integer id) {
        Optional<InputProduct> optional = inputProductRepository.findById(id);
        return optional.orElse(null);
    }
}
