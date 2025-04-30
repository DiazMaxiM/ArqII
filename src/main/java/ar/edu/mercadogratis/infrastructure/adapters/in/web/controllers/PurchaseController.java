package ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers;


import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.usecase.CreatePurchaseUseCase;
import ar.edu.mercadogratis.usecase.ListPurchaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.mercadogratis.application.service.PurchaseService;
import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import ar.edu.mercadogratis.domain.model.PurchaseRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final CreatePurchaseUseCase createPurchaseUseCase;
    private final ListPurchaseUseCase listPurchaseUseCase;

    @PostMapping
    public ResponseEntity<PurchaseProduct> createPurchase(
            @Valid @RequestBody PurchaseRequest purchaseRequest) {
        PurchaseProduct saved = createPurchaseUseCase.createPurchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @GetMapping
    public ResponseEntity<Iterable<PurchaseProduct>> listPurchases(@RequestBody User user) {
        Iterable<PurchaseProduct> list = listPurchaseUseCase.listPurchases(user.getEmail());
        return ResponseEntity.ok(list);
    }
}
