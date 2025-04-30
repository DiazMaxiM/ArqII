package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import ar.edu.mercadogratis.domain.model.PurchaseRequest;

public interface CreatePurchaseUseCase {
    public PurchaseProduct createPurchase(PurchaseRequest purchaseRequest);
}
