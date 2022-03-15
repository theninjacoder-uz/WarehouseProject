package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.OutputProductReceiveDto;
import uz.pdp.warehouseproject.dto.receive.OutputReceiveDto;
import uz.pdp.warehouseproject.service.OutputProductService;
import uz.pdp.warehouseproject.service.OutputService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/output-product")
public class OutputProductController {

    private final OutputProductService outputProductService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OutputProductReceiveDto outputDto){
        return ResponseEntity.ok(outputProductService.add(outputDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(outputProductService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(outputProductService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody OutputProductReceiveDto outputDto){
        return ResponseEntity.ok(outputProductService.edit(id, outputDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(outputProductService.delete(id));
    }
}
