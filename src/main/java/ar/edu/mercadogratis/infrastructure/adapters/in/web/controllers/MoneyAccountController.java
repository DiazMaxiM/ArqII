package ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers;



import ar.edu.mercadogratis.usecase.AddFundsMoneyUseCase;
import ar.edu.mercadogratis.usecase.GetFundsMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.mercadogratis.domain.model.AddFundsRequest;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/money_account")
@RequiredArgsConstructor
public class MoneyAccountController {


    private final AddFundsMoneyUseCase AddFundsMoneyUseCase;
    private final GetFundsMoneyUseCase getFundsMoneyUseCase;

    @PostMapping
    public ResponseEntity<BigDecimal> addFunds(@Valid @RequestBody AddFundsRequest addFundsRequest) {
        return ResponseEntity.ok(AddFundsMoneyUseCase.addFunds(addFundsRequest));
    }

    @GetMapping
    public ResponseEntity<BigDecimal> getFunds(@RequestParam Long userId) {
        return ResponseEntity.ok(getFundsMoneyUseCase.getFunds(userId));
    }
}
