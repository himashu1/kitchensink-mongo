package org.jboss.as.quickstarts.kitchensink.controllers;


import org.jboss.as.quickstarts.kitchensink.models.MemberEntity;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberMongoRepositoryImpl;
import org.jboss.as.quickstarts.kitchensink.services.MemberRegistration;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
@Validated
public class MemberRestController {

    @Autowired
    private MemberMongoRepositoryImpl mongoDbMemberRepository;

    @Autowired
    private MemberRegistration registration;

    // GET all members, ordered by name
    @GetMapping
    public List<MemberEntity> listAllMembers() {
        return mongoDbMemberRepository.findAll();
    }

//     GET member by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberEntity> lookupMemberById(@PathVariable("id") long id) {
        MemberEntity member = mongoDbMemberRepository.findById(id);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // POST to create a new member
    @PostMapping
    public ResponseEntity<?> createMember( @RequestBody MemberEntity member) {
        try {
            validateMember(member);
            registration.register(member);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConstraintViolationException ce) {
            return createViolationResponse(ce.getMessage());
        }  catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }
    }

    // Validation logic for member
    private void validateMember(MemberEntity member) throws Exception {

        // Check if email already exists
        if (emailAlreadyExists(member.getEmail())) {
            throw new Exception("Email already taken");
        }
    }

    private ResponseEntity<Map<String, String>> createViolationResponse(String violation) {
        Map<String, String> responseObj = new HashMap<>();

        //responseObj.put(DataIntegrityViolationException.class.toString(), violation);
        responseObj.put("DataIntegrityViolationException", violation);
        return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
    }

    // Check if the email already exists in the database
    public boolean emailAlreadyExists(String email) {
        MemberEntity foundMember =  mongoDbMemberRepository.findByEmail(email);
        return foundMember != null;
    }
}
