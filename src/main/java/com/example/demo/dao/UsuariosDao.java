package com.example.demo.dao;

import com.example.demo.Exceptions.MailException;
import com.example.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuariosDao {

    Usuario getUsuarios(int str);

    Usuario registrarUsuario(Usuario usuario) throws MailException;

    List<Usuario>  getUsuarioByMail(String email);
}
