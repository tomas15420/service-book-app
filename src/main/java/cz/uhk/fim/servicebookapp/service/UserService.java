package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.dto.UserRegisterDto;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public void registerUser(UserRegisterDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto,User.class);
        userRepository.save(user);
    }

}
