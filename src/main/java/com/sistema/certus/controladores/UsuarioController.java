package com.sistema.certus.controladores;


import com.sistema.certus.modelo.Pelicula;
import com.sistema.certus.modelo.Usuario;
import com.sistema.certus.repositorios.UsuarioRepository;
import com.sistema.certus.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository ElRepositorio;
    @Autowired
    private UsuarioServicio LosServicios;
//METODOS PARA CAMBIOS DE PAGINAS
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
        public String mostrarPerfil(Model model) {



            Usuario usuarioRegistrado;
            usuarioRegistrado = LosServicios.obtenerUsuarioRegistrado();
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
    public String register(@ModelAttribute Usuario usuarios) {
        Usuario registrando = LosServicios.RegistrarUser(usuarios.getNombre(), usuarios.getApellido(), usuarios.getTelefono(),usuarios.getEmail(),usuarios.getContrasena());
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



}




