package com.scm.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="users")
// If we use lambok then no need create getter and setter just use @Annotations of the @getter and @setter
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class User implements UserDetails {
  
    @Id
    private String userId;
    @Column(name="user_name", nullable = false)

    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    
    @Getter(AccessLevel.NONE)
    private String password;
    
    @Column(length = 300)
    private String about;
    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;
    @Getter(value = AccessLevel.NONE)
    // information

    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    @Enumerated(value = EnumType.STRING)
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKDIN, GITHUB
    private Providers provider=Providers.SELF;
    private String providerUserId;

    // mapping from Contact table
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList=new ArrayList<>();

    private String emailToken; 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER,ADMIN]
        // collection of simpleGrantedAuthority [roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return roles;   
    }

    // for this project our e-mail id is username

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
