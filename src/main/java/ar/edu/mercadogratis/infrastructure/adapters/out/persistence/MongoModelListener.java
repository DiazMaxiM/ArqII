// MongoModelListener.java
package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;

import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.MoneyAccountDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.UserDocument;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoModelListener extends AbstractMongoEventListener<Object> {

    private final SequenceGeneratorService seqGen;

    public MongoModelListener(SequenceGeneratorService seqGen) {
        this.seqGen = seqGen;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();

        if (source instanceof UserDocument) {
            UserDocument u = (UserDocument) source;
            if (u.getId() == null) {
                u.setId(seqGen.generateSequence("users_sequence"));
            }
        }
        else if (source instanceof ProductDocument) {
            ProductDocument p = (ProductDocument) source;
            if (p.getId() == null) {
                p.setId(seqGen.generateSequence("products_sequence"));
            }
        }else if (source instanceof MoneyAccountDocument) {
            MoneyAccountDocument m = (MoneyAccountDocument) source;
            if (m.getId() == null) {
                m.setId(seqGen.generateSequence("money_accounts_sequence"));
            }
        }

        // agrega más entidades si necesitas…
    }
}
