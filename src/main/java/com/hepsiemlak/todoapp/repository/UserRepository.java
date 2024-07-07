package com.hepsiemlak.todoapp.repository;

import com.hepsiemlak.todoapp.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

}

