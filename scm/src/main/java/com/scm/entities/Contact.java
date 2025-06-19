package com.scm.entities;

//import org.hibernate.mapping.List;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length=1000)
    private String description;
    private boolean favorite=false;

    private String webSiteLink;
    private String linkdinId;

    
    //private List<String> socialLink=new ArrayList<>();

    // Mapping form User table
    @ManyToOne
    private User user;


    // mapping from SocialLink table
   @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SocialLink> links=new ArrayList<>();
}
