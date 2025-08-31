package vortex.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vortex.backend.dto.UserProfileDTO;
import vortex.backend.dto.UserUpdateDTO;
import vortex.backend.entities.User;
import vortex.backend.services.UserService;

@RestController
@RequestMapping("/usuarios")
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

    @GetMapping("/perfil")
    public ResponseEntity<UserProfileDTO> getLoggedUserProfile() {
        try {
            UserProfileDTO userProfile = userService.getLoggedUserProfile();
            return ResponseEntity.ok(userProfile);
        } catch (IllegalStateException e) {
            // Retorna 401 Unauthorized se não houver usuário logado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/perfil")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@RequestBody UserUpdateDTO updateData) {
        UserProfileDTO updatedProfile = userService.updateUserProfile(updateData);
        return ResponseEntity.ok(updatedProfile);
    }
    
}