package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.OutputReceiveDto;
import uz.pdp.warehouseproject.service.OutputService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/output")
public class OutputController {

    private final OutputService outputService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OutputReceiveDto outputDto){
        return ResponseEntity.ok(outputService.add(outputDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(outputService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(outputService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody OutputReceiveDto outputDto){
        return ResponseEntity.ok(outputService.edit(id, outputDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(outputService.delete(id));
    }
}
