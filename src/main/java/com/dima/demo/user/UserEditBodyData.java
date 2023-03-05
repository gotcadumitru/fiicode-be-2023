package com.dima.demo.user;

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
    private final String phoneNo;
    private final String cnp;
    private final String address;
}
