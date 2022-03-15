package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.OutputProductReceiveDto;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.entity.OutputProduct;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.repository.OutputProductRepository;
import uz.pdp.warehouseproject.repository.OutputRepository;
import uz.pdp.warehouseproject.repository.ProductRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OutputProductService implements BaseService<OutputProductReceiveDto, OutputProduct> {

    private final OutputProductRepository outputProductRepository;
    private final ProductRepository productRepository;
    private final OutputRepository outputRepository;

    @Override
    public ApiResponse add(OutputProductReceiveDto item) {
        Optional<Output> optionalOutput = outputRepository.findById(item.getOutputId());
        if(optionalOutput.isEmpty())
            return new ApiResponse(4, "Output not found", null);

        //todo check if product available and not less than amount
        Optional<Product> optionalProduct = productRepository.findById(item.getProductId());
        if(optionalProduct.isEmpty())
            return new ApiResponse(4, "Product not found", null);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(item.getAmount());
        outputProduct.setPrice(item.getPrice());

        return new ApiResponse(1, SAVED, outputProductRepository.save(outputProduct));
    }

    @Override
    public ApiResponse edit(Integer id, OutputProductReceiveDto item) {

        Optional<OutputProduct> optional = outputProductRepository.findById(id);
        if(optional.isEmpty())
            return NOT_FOUND;
        OutputProduct outputProduct = optional.get();
        Optional<Output> optionalOutput = outputRepository.findById(item.getOutputId());
        if(optionalOutput.isEmpty())
            return new ApiResponse(4, "Output not found", null);


        //todo check if product available and not less than amount
        if(item.getProductId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(item.getProductId());
            if (optionalProduct.isEmpty())
                return new ApiResponse(4, "Product not found", null);

            outputProduct.setProduct(optionalProduct.get());
        }

        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(item.getAmount());
        outputProduct.setPrice(item.getPrice());
        outputProductRepository.save(outputProduct);

        return new ApiResponse(1, EDIT, outputProduct);
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All output products", outputProductRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {
        OutputProduct outputProduct = checkById(id);
        if(outputProduct == null)
            return NOT_FOUND;
        return new ApiResponse(1, "Output Product", outputProduct);
    }

    @Override
    public OutputProduct checkById(Integer id) {
        Optional<OutputProduct> optional = outputProductRepository.findById(id);
        return optional.orElse(null);
    }
}
