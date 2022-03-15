package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.service.MeasurementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Measurement measurement){
        return ResponseEntity.ok(measurementService.add(measurement));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(measurementService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(measurementService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody Measurement measurement){
        return ResponseEntity.ok(measurementService.edit(id, measurement));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(measurementService.delete(id));
    }

}
