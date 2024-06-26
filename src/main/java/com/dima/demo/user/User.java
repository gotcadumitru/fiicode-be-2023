package com.dima.demo.user;

import com.dima.demo.authentication.Provider;
import com.dima.demo.doctor.Doctor;
import com.dima.demo.message.Message;
import com.dima.demo.storage.Storage;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String address;
    private String cnp;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL)
    private Storage profileImage;

    private Boolean isLocked;
    private Boolean isEnabled;

    @OneToOne(cascade = CascadeType.ALL)
    private Doctor doctor;

    public User(String firstName, String lastName, String email, String password, UserRole role, Provider provider, Boolean isLocked, Boolean isEnabled,String phoneNo,String address,String cnp,@Nullable Doctor doctor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.isLocked = isLocked;
        this.isEnabled = isEnabled;
        this.phoneNo = phoneNo;
        this.address = address;
        this.cnp = cnp;
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
