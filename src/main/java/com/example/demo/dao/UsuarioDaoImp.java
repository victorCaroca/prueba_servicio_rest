package com.example.demo.dao;

import com.example.demo.Exceptions.MailException;
import com.example.demo.models.Phone;
import com.example.demo.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuariosDao{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Usuario getUsuarios(int str) {
        Usuario usuario = null;
        String query= new String();
        query = "From Usuario where id="+str;

        entityManager.createQuery(query).getResultList();
        List<Usuario> lista=  entityManager.createQuery(query).getResultList();
        if(!lista.isEmpty()) {
            usuario = lista.get(0);
        }
        return usuario;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws MailException {

        String query= new String();
        query = "From Usuario where email='"+usuario.getEmail()+"'";

        List<Usuario> correos=entityManager.createQuery(query).getResultList();

        if(!correos.isEmpty()) {
            MailException e=new MailException("Mail incorrecto");

            throw  e;
        }




        entityManager.merge(usuario);





       // List<Phone> listaTelefonos=usuario.getPhones();

        List<Usuario> lista=  entityManager.createQuery(query).getResultList();








        Usuario newUsuario=new Usuario();
        if(!lista.isEmpty()) {
            newUsuario = lista.get(0);
        }
        return newUsuario;
    }

    @Override
    public List<Usuario> getUsuarioByMail(String email) {
        String query= new String();
        query = "From Usuario where email='"+email+"'";

        List<Usuario> correos=entityManager.createQuery(query).getResultList();

        return correos;
    }
}
