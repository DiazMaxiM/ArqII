package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.PurchaseProduct;

public interface ListPurchaseUseCase {
    public Iterable<PurchaseProduct> listPurchases(String buyerEmail);
}
