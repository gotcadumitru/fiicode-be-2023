package com.dima.demo.user;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEditBodyData {
        private final String firstName;
        private final String lastName;
        private final String email;
        @Nullable
        private final String password;
        private final String phoneNo;
        private final String address;
        private final String cnp;
}
