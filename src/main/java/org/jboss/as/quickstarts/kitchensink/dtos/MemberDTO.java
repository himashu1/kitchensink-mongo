package org.jboss.as.quickstarts.kitchensink.dtos;

import org.jboss.as.quickstarts.kitchensink.models.MemberEntity;

public record MemberDTO(
        long id,
        String name,
        String email,
        String phoneNumber)
    {

        public MemberDTO(MemberEntity memberEntity) {
            this(memberEntity.getId(), memberEntity.getName(),
                    memberEntity.getEmail(), memberEntity.getPhoneNumber());
        }

    public MemberEntity toMemberEntity() {
        return new MemberEntity(0, name, email, phoneNumber);
    }
}
