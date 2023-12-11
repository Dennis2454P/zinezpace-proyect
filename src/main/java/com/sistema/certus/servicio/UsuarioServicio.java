package com.sistema.certus.servicio;

import com.sistema.certus.enumeraciones.Rol;
import com.sistema.certus.excepciones.MiException;
import com.sistema.certus.modelo.Usuario;
import com.sistema.certus.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {
 @Autowired
    private UsuarioRepository DataBasexd;


 public Usuario RegistrarUser(String nombre, String apellido, int telefono, String email, String contrasena){
     Usuario user = new Usuario();
     user.setNombre(nombre);
     user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setEmail(email);
        user.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
        user.setRol(Rol.USER);
     return DataBasexd.save(user);
 }
 public Usuario validarUser(String nombre, String contrasena ){
     return DataBasexd.findByNombreAndContrasena(nombre,contrasena).orElse(null);

 }
public Usuario mostrarUsuario(Long id){
     return DataBasexd.findByid(id);
}




    //NUEVOS SERVICIOS CON SEGURIDAD

   public Usuario Registrar2(String nombre, String apellido, int telefono, String email, String contrasena) throws MiException {
      validar(nombre,apellido,telefono,email,contrasena);
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setEmail(email);
        user.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
        user.setRol(Rol.USER);
        return DataBasexd.save(user);

    }
    private void  validar(String nombre,String apellido, int telefono, String email, String contrasena) throws MiException{
     if(nombre.isEmpty()|| nombre == null){
         throw new MiException("El nombre no puede estar vacio");
        }
        if(email.isEmpty()|| email == null){
            throw new MiException("El email no puede estar vacio");
        }
        if(contrasena.isEmpty()|| contrasena == null){
            throw new MiException("la contraseña no puede estar vacia");
        }
        if(apellido.isEmpty()|| apellido == null){
            throw new MiException("el apellido no puede estar vacia");
        }
        if(telefono ==0){
            throw new MiException("el telfono puede estar vacia");
        }}

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = DataBasexd.BuscarPorNombre(nombre);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
          GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
          permisos.add(p);
            ServletRequestAttributes attr =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("sesionguardada", usuario);
            return new User(usuario.getNombre(), usuario.getContrasena(),permisos );
        }else {return null;
        }
    }
}
