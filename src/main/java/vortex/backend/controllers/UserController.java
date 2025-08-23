package vortex.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vortex.backend.entities.User;
import vortex.backend.services.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<User> cadastrarUsuario(@RequestBody User usuario) {
        try {
            User novoUsuario = userService.cadastrarUsuario(usuario);
            novoUsuario.setSenha(null); 
            
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (IllegalStateException e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}