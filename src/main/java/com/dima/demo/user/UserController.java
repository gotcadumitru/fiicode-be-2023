package com.dima.demo.user;

import com.dima.demo.patient.PatientInviteBodyData;
import com.dima.demo.patient.PatientTransferBodyData;
import com.dima.demo.security.config.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "me")
    public User getAuthUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        return userService.getUserByJwtToken(jwtUtils.getFromHeader(authorizationHeader));
    }

    @PutMapping(path = "{userId}")
    public User editUserData(@PathVariable @NonNull Long userId, @RequestBody UserEditBodyData request) {
        return userService.editUser(userId, request);
    }
}
