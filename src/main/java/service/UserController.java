package service;

import model.dto.UserDTO;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class UserController implements UserService {

   UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDTO login(String email,String password) {
        return userRepository.findPerson(email,password);
    }
}
