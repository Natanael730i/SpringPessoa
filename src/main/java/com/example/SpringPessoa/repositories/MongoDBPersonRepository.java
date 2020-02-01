package com.example.SpringPessoa.repositories;

import com.example.SpringPessoa.model.Pessoa;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Service
@Repository
public abstract class MongoDBPersonRepository implements PersonRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();

    private MongoClient client;
    private MongoCollection<Pessoa> personCollection;

    @PostConstruct
    void init() {
        personCollection = client.getDatabase("test").getCollection("persons", Pessoa.class);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        pessoa.setId(new ObjectId());
        personCollection.insertOne(pessoa);
        return pessoa;
    }

    @Override
    public List<Pessoa> findAll() {
        return personCollection.find().into(new ArrayList<>());
    }


    @Override
    public long delete(ObjectId id) {
        return personCollection.deleteOne(eq("_id", new ObjectId(String.valueOf(id)))).getDeletedCount();
    }


    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }
}
