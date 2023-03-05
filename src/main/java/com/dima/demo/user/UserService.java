package com.dima.demo.user;

import com.dima.demo.authentication.Provider;
import com.dima.demo.exception.ApiRequestException;
import com.dima.demo.oauth2.CustomOAuth2User;
import com.dima.demo.registration.token.ConfirmationTokenService;
import com.dima.demo.security.config.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final JwtUtils jwtUtils;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public User signUpUser(User user) {
        Optional<User> optionalUserFromDB = userRepository.findByEmail(user.getEmail());

        if (optionalUserFromDB.isPresent()) {
            if (optionalUserFromDB.get().getIsEnabled()) {
                throw new ApiRequestException("email already taken");
            } else {
                deleteUserById(optionalUserFromDB.get().getId());
            }
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User editUser(Long userId, UserEditBodyData requestData) {
        User user = getUserById(userId);
        user.setCnp(requestData.getCnp());
        user.setEmail(requestData.getEmail());
        user.setFirstName(requestData.getFirstName());
        user.setLastName(requestData.getLastName());
        user.setAddress(requestData.getAddress());
        user.setPhoneNo(requestData.getPhoneNo());
        return userRepository.save(user);
    }

    public void enableUser(String email) {
        userRepository.enableUserByEmail(email);
    }

    public void deleteUserById(Long userId) {
        confirmationTokenService.deleteConfirmationTokensByUserId(userId);
        userRepository.deleteById(userId);
    }

    public User processOAuthPostLogin(CustomOAuth2User user, Provider oauth2ClientName) {
        Optional<User> existUser = userRepository.findByEmail(user.getEmail());

        if (!existUser.isPresent()) {
            User newUser = new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    bCryptPasswordEncoder.encode(user.getEmail()+"secret"),
                    UserRole.PATIENT,
                    oauth2ClientName,
                    false,
                    true,
                    "",
                    "",
                    "",
                    null
            );
            userRepository.save(newUser);
            return null;
        }
        return existUser.get();
    }

    public User getUserByJwtToken(String jwtToken) {
        return userRepository.findByEmail(jwtUtils.extractUsername(jwtToken)).orElseThrow(() -> new ApiRequestException("User not found ( from jwt token )"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("User not found, cho za nah?"));
    }

    public List<User> getUsersByEmail(List<String> emails) {
        return userRepository.findAllByEmailIn(emails);
    }
}
