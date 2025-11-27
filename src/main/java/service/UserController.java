package service;

import model.dto.UserDTO;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class UserController implements UserService {

    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDTO login(String name, String email, String password) {
        return userRepository.findPerson(name,email, password);
    }

    @Override
    public boolean register(UserDTO user) {
        return userRepository.insertUser(user);
    }
}
