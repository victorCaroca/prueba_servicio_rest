package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@ToString @EqualsAndHashCode
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="UUID")
    //@SequenceGenerator(name="UUID",sequenceName = "id_seq",allocationSize = 10)
    @Column(name = "idUsuario")
    public Integer id;
    @Getter @Setter
    @Column(name = "created")
    public Date create;
    @Getter @Setter
    @Column(name = "modified")
    public Date modified;
    @Getter @Setter
    @Column(name = "lastLogin")
    public Date lastLogin;
    @Getter @Setter
    @Column(name = "token")
    public String token;
    @Getter @Setter
    @Column(name = "isActive")
    public int isActive;
    @Getter @Setter
    @Column(name = "name")
    public String name;
    @Getter @Setter
    @Column(name = "email")
    public String email;
    @Getter @Setter
    @Column(name = "password")
    public String password;


    @Getter @Setter
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany(mappedBy = "usuario",orphanRemoval=true)
    public List<Phone> phones;

    public Usuario() {
    }
}
