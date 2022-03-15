package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Currency currency){
        return ResponseEntity.ok(currencyService.add(currency));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(currencyService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(currencyService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody Currency currency){
        return ResponseEntity.ok(currencyService.edit(id, currency));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(currencyService.delete(id));
    }
}
