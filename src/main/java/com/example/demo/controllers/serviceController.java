package com.example.demo.controllers;

import com.example.demo.Exceptions.MailException;
import com.example.demo.dao.UsuariosDao;
import com.example.demo.models.Usuario;
import com.example.demo.models.Phone;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.utils.jwtUtils;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.hibernate.annotations.GenericGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class serviceController {

    @Autowired
    private UsuarioRepository usuarioDao;

    @Autowired
    private UsuariosDao usuarioDaoImpl;

    @Autowired
    private PhoneRepository phoneDao;

    @Autowired
    private jwtUtils jwt;


    @RequestMapping(value="usuario/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuario(@PathVariable Integer idUser){
            Map<String,Object> response=new HashMap<>();
            Optional<Usuario> usuario= usuarioDao.findById(idUser);

            if(usuario.isPresent()){
                return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
            }else{
                response.put("mensaje", "Dato no encontrado");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }

    }


    @RequestMapping(value="usuario", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
        Map<String,Object> response=new HashMap<>();


        String regx = "[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(usuario.getEmail());
        if(!matcher.matches()){
            response.put("mensaje", "Correo ingresado incorrectamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        String regPass="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.;:^&+=])(?=\\S+$).{8,20}$";
        Pattern patternPass = Pattern.compile(regPass);
        Matcher matcherPass = patternPass.matcher(usuario.getPassword());
        boolean b=matcherPass.matches();
        if(!matcherPass.matches()){
            response.put("mensaje", "Password con mal formato" );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Argon2 argon= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String codi=new String();
        codi=argon.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(codi);
        Date hoy= new Date();
        usuario.setCreate(hoy);
        usuario.setLastLogin(hoy);
        usuario.setModified(hoy);




        List<Usuario> isMail=usuarioDaoImpl.getUsuarioByMail(usuario.getEmail());
        if(!isMail.isEmpty()) {
            response.put("mensaje", "Correo ya registrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }


        usuario.setToken(jwt.create(String.valueOf(usuario.getId()),"post"));

        Usuario newUsuario = new Usuario();
        newUsuario = usuarioDao.save(usuario);

          for(int i=0; i<newUsuario.getPhones().size();i++){

               Phone localPhone=newUsuario.getPhones().get(i);
               localPhone.setId(newUsuario.getPhones().get(i).getId());
               localPhone.setUsuario(newUsuario);
               phoneDao.save(localPhone);
           }

           if (newUsuario == null) {
               response.put("mensaje", "Dato no encontrado");
               return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
           } else {
               return new ResponseEntity<Usuario>(newUsuario, HttpStatus.OK);
           }

    }


    @RequestMapping(value="usuario/{idUser}", method = RequestMethod.PATCH)
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer idUser,@RequestBody Usuario usuario){

        Map<String,Object> response=new HashMap<>();

        Optional<Usuario> user= usuarioDao.findById(idUser);

        if(user.isPresent()){
            Usuario local=usuario;
            local.setId(user.get().getId());
            local.setPassword(user.get().getPassword());
            Date hoy= new Date();
            local.setCreate(user.get().getCreate());
            local.setLastLogin(user.get().getLastLogin());
            local.setModified(hoy);
            local.setToken(user.get().getToken());
            usuarioDao.save(local);
            return new ResponseEntity<Usuario>(local, HttpStatus.OK);
        }else{
            response.put("mensaje", "Dato no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value="usuario/{idUser}", method = RequestMethod.DELETE)
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer idUser){
        Map<String,Object> response=new HashMap<>();

        Optional<Usuario> user= usuarioDao.findById(idUser);

        if(user.isPresent()){

            for(int i=0; i<user.get().getPhones().size();i++){
                Phone tmpPhone=user.get().getPhones().get(i);
                phoneDao.delete(tmpPhone);
            }
            usuarioDao.delete(user.get());
            response.put("mensaje", "Usuario Eliminado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }else{
            response.put("mensaje", "Dato no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

    }



}
