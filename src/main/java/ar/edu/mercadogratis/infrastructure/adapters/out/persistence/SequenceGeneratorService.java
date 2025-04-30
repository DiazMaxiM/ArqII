// SequenceGeneratorService.java
package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;

import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.DatabaseSequence;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    private final MongoOperations mongo;

    public SequenceGeneratorService(MongoOperations mongo) {
        this.mongo = mongo;
    }

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongo.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );
        return (counter != null) ? counter.getSeq() : 1L;
    }
}