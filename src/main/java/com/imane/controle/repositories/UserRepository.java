package com.imane.controle.repositories;

import com.imane.controle.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT l FROM User l WHERE l.username = :username AND l.password = :password")
    public User verifie(@Param("username") String username, @Param("password") String password);
}
