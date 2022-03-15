package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.repository.MeasurementRepository;
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
public class MeasurementService implements BaseService<Measurement, Measurement> {

    private final MeasurementRepository measurementRepository;

    @Override
    public ApiResponse add(Measurement item) {
        //TODO make measurement name unique and handle Exception
        boolean exists = measurementRepository.existsByNameAndActive(item.getName(), true);
        if(exists)
            return new ApiResponse(4, "Measurement name already exists", null);
        return new ApiResponse(1, SAVED,  measurementRepository.save(item));
    }

    @Override
    public ApiResponse edit(Integer id, Measurement item) {

        Measurement measurement = checkById(id);
        if(measurement == null)
            return NOT_FOUND;

        measurement.setName(item.getName());
        measurementRepository.save(measurement);
        return new ApiResponse(1, EDIT, measurement);
    }

    @Override
    public ApiResponse delete(Integer id) {

        Measurement measurement = checkById(id);
        if(measurement == null)
            return NOT_FOUND;

        measurement.setActive(false);
        measurementRepository.save(measurement);

        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        List<Measurement> all = measurementRepository.findAll();
        return new ApiResponse(1, "All measurements", all);
    }

    @Override
    public ApiResponse getById(Integer id) {
        Measurement measurement = checkById(id);
        if(measurement == null)
            return NOT_FOUND;

        return new ApiResponse(1, "Measurement", measurement);
    }


    @Override
    public Measurement checkById(Integer id) {

        Optional<Measurement> optional = measurementRepository.findById(id);
        if(optional.isPresent() && optional.get().isActive())
            return optional.get();

        return null;
    }
}
