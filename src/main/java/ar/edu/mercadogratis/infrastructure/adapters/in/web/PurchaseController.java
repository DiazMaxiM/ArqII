package ar.edu.mercadogratis.infrastructure.adapters.in.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.mercadogratis.application.service.PurchaseService;
import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import ar.edu.mercadogratis.domain.model.PurchaseRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseProduct> createPurchase(@Valid @RequestBody PurchaseRequest purchaseRequest) {
        PurchaseProduct purchase = purchaseService.createPurchase(purchaseRequest);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping
    public ResponseEntity<Iterable<PurchaseProduct>> listPurchases(@RequestParam String buyerEmail) {
        return ResponseEntity.ok(purchaseService.listPurchases(buyerEmail));
    }
}
