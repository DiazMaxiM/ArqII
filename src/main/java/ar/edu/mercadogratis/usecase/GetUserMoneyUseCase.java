package ar.edu.mercadogratis.usecase;

import java.math.BigDecimal;

public interface GetUserMoneyUseCase {
    BigDecimal getUserMoney(Long userId);
}
