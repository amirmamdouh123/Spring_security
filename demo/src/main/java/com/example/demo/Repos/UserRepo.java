package com.example.demo.Repos;

import com.example.demo.Entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserApp,Long> {

    @Query("select u from UserApp u where username=:username")
    Optional<UserApp> getByUsername(String username);
}
