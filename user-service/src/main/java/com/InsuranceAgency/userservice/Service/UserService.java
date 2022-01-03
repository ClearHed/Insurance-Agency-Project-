package com.InsuranceAgency.userservice.Service;


import com.InsuranceAgency.userservice.repo.UserRepo;
import com.InsuranceAgency.userservice.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {
    public final UserRepo userRepo;

    public List<User> getAllUsers(){
        List<User> users = userRepo.findAll();
        return users;
    }

    public User getUserByID(int id) throws IllegalArgumentException{
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()){
            throw new IllegalArgumentException();

        }else{
            return user.get();
        }
    }
    public int createUser(String username, String password, String email) throws IllegalArgumentException{
        User user = new User(username, password, email);
        User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public void updateUser(Integer id, String username, String password, String email) throws IllegalArgumentException{
        final Optional<User> ID = userRepo.findById(id);
        if (ID.isEmpty())
            throw new IllegalArgumentException("Invalid User ID");
        final User user = ID.get();
        if (username != null && !username.isBlank()) user.setUsername(username);
        if (password != null && !password.isBlank()) user.setPassword(password);
        if (email != null && !email.isBlank()) user.setEmail(email);

        userRepo.save(user);
    }

    public void delete(int id){
        userRepo.deleteById(id);
    }
}
