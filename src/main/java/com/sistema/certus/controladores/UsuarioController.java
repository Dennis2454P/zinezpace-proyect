package com.sistema.certus.controladores;


import com.sistema.certus.excepciones.MiException;
import com.sistema.certus.modelo.Pelicula;
import com.sistema.certus.modelo.Usuario;
import com.sistema.certus.repositorios.UsuarioRepository;
import com.sistema.certus.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository ElRepositorio;
    @Autowired
    private UsuarioServicio LosServicios;
//METODOS PARA CAMBIOS DE PAGINAS
  @GetMapping("asientos")
  public String mostrarasientos(){
      return "asientos";
  }
    @GetMapping("/register")
    public String MostrarRegistroPage(Model variable){
        variable.addAttribute("EnvioDeRegistro", new Usuario());
        return "Registro";
    }

    @GetMapping("/login")
    public String MostrarLogin(Model variable){
        variable.addAttribute("EnvioDeLogin",new Usuario());
        return "Login";
    }

     @GetMapping("/perfil")
        public String mostrarPerfil(Model model, Principal principal) {

         String nombreUsuario = principal.getName();

            Usuario usuarioRegistrado;
            usuarioRegistrado = ElRepositorio.BuscarPorNombre(nombreUsuario);
            if (usuarioRegistrado != null) {
                model.addAttribute("Nombre", usuarioRegistrado.getNombre());
                model.addAttribute("apellido", usuarioRegistrado.getApellido());
                model.addAttribute("telefono", usuarioRegistrado.getTelefono());
                model.addAttribute("email", usuarioRegistrado.getEmail());
                return "perfil";
            } else {
              return "errores";
            }


        }  /*
       @GetMapping("/perfil/{id}")
        public String mostrarPerfil(@PathVariable Long id, Model model) {
            Usuario usuarioRegistrado = ElRepositorio.findByid(id);

            if (usuarioRegistrado != null) {

                model.addAttribute("Nombre", usuarioRegistrado.getNombre());
                model.addAttribute("apellido", usuarioRegistrado.getApellido());
                model.addAttribute("telefono", usuarioRegistrado.getTelefono());
                model.addAttribute("email", usuarioRegistrado.getEmail());

                return "perfil";
            } else {
                return "errores";
            }
        }*/
//METODOS DE ENVIO DE DATOS

    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuarios, Model model) {

        Usuario registrando = LosServicios.RegistrarUser(usuarios.getNombre(), usuarios.getApellido(), usuarios.getTelefono(), usuarios.getEmail(), usuarios.getContrasena());
        return registrando == null ? "errores" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuarios, Model model) {

        Usuario autenticando = LosServicios.validarUser(usuarios.getNombre(), usuarios.getContrasena());
        if (autenticando != null) {
            Usuario obtener = LosServicios.mostrarUsuario(autenticando.getId());
            model.addAttribute("Nombre", obtener.getNombre());
            model.addAttribute("apellido", obtener.getApellido());
            model.addAttribute("telefono", obtener.getTelefono());
            model.addAttribute("email", obtener.getEmail());
            model.addAttribute("contrasena", obtener.getContrasena());


            return "perfil";
        } else {
            return "errores";
        }
    }




//NUevos metodos
@GetMapping("/registro1")
public String Mostrar(){
    return "registro1";
}
    @PostMapping("/Registro1")
    public String Registro1(@RequestParam String nombre, @RequestParam String apellido, @RequestParam int telefono , @RequestParam String email, @RequestParam String contrasena, ModelMap modelo){
        try {
            LosServicios.Registrar2(nombre, apellido, telefono, email, contrasena);

            return "index";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre",nombre);
            return "registro1";
        }
    }
    @GetMapping("/login1")
    public String Mostrarlogin(@RequestParam(required = false)String error,ModelMap modelo){
        if(error!= null){
            modelo.put("error","tienes un error");
        }

        return "login1";
    }
}




