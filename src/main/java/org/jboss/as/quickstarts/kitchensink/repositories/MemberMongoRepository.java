package org.jboss.as.quickstarts.kitchensink.repositories;

import org.jboss.as.quickstarts.kitchensink.models.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMongoRepository {
    MemberEntity save(MemberEntity memberEntity);

    MemberEntity findByEmail(String email);

    List<MemberEntity> findAll();

    MemberEntity findById(long id);
}
