package org.jboss.as.quickstarts.kitchensink.models;

import org.springframework.context.ApplicationEvent;

public class MemberRegisteredEvent extends ApplicationEvent {

    private final MemberEntity member;

    public MemberRegisteredEvent(Object source, MemberEntity member) {
        super(source);
        this.member = member;
    }

    public MemberEntity getMember() {
        return member;
    }
}
