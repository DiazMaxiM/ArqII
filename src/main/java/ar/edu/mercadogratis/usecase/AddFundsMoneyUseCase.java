package ar.edu.mercadogratis.usecase;


import ar.edu.mercadogratis.domain.model.AddFundsRequest;

import java.math.BigDecimal;

public interface AddFundsMoneyUseCase {
    BigDecimal addFunds(AddFundsRequest request);
}
