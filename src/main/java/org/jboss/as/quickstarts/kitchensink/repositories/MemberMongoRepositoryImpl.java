package org.jboss.as.quickstarts.kitchensink.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.jboss.as.quickstarts.kitchensink.models.MemberEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MemberMongoRepositoryImpl implements MemberMongoRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    private final MongoClient client;

    private MongoCollection<MemberEntity> memberCollection;

    public MemberMongoRepositoryImpl(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        memberCollection = client.getDatabase("member-db").getCollection("members", MemberEntity.class);
    }

    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        //memberEntity.setId(System.currentTimeMillis());
        memberCollection.insertOne(memberEntity);
        return memberEntity;
    }

    @Override
    public MemberEntity findByEmail(String email) {
        //return null;
        //return memberCollection.find());
        return memberCollection.find(eq("email", email)).first();
    }
    @Override
    public List<MemberEntity> findAll() {
        return memberCollection.find().into(new ArrayList<>());
    }

    @Override
    public MemberEntity findById(long id) {
        return memberCollection.find(eq("_id", id)).first();
    }
}
