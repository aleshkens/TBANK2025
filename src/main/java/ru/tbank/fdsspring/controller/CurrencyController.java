package ru.tbank.fdsspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @GetMapping
    public ResponseEntity<?> getCurrencies(){
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrency(@PathVariable int id){
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<?> addCurrency(){
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable int id){
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable int id){
        return ResponseEntity.ok("OK");
    }

}
