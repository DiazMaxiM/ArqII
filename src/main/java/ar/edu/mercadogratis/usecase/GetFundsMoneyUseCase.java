package ar.edu.mercadogratis.usecase;

import java.math.BigDecimal;

public interface GetFundsMoneyUseCase {
    BigDecimal getFunds(Long userId);
}
