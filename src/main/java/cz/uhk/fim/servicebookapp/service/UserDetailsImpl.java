package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsernameOrEmail(username).orElseThrow(() -> new UsernameNotFoundException("Uživatel s tímto uživatelským jménem neexistuje"));
    }
}
