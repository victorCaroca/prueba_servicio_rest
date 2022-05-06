package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
@Table(name = "phones")
public class Phone {



    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhone")
    public String id;

    @Getter @Setter
    @Column(name = "number")
    public String number;

    @Getter @Setter
    @Column(name = "cityCode")
    public String cityCode;

    @Getter @Setter
    @Column(name = "countryCode")
    public String countryCode;


    @Setter
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    public Usuario usuario;


    public Usuario getUsuario() {
        return this.usuario;
    }
    public void Usuario(Usuario b) {
        this.usuario = b;
    }

    public Phone() {

    }
}
