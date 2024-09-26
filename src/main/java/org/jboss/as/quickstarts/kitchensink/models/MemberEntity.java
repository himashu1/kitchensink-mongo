package org.jboss.as.quickstarts.kitchensink.models;

import java.util.Objects;

public class MemberEntity {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;

    public MemberEntity() {
    }

    public MemberEntity(long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\''  + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity memberEntity = (MemberEntity) o;
        return id == memberEntity.id && Objects.equals(name, memberEntity.name) && Objects.equals(
                email, memberEntity.email) && Objects.equals(phoneNumber, memberEntity.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber);
    }
}
