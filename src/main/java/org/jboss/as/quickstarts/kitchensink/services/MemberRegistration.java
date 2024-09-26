package org.jboss.as.quickstarts.kitchensink.services;


import org.jboss.as.quickstarts.kitchensink.models.MemberEntity;
import org.jboss.as.quickstarts.kitchensink.models.MemberRegisteredEvent;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberMongoRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberRegistration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberRegistration.class);

    @Autowired
    private MemberMongoRepositoryImpl em;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //@Transactional
    public void register(MemberEntity member) throws Exception {
        LOGGER.info("Registering " + member.getName());
        em.save(member);

        // Fire an event using Spring's ApplicationEventPublisher
        eventPublisher.publishEvent(new MemberRegisteredEvent(this, member));
    }
}
