package com.pkg.spring.msvc.msvcsupabase.security.controller;

import com.pkg.spring.msvc.msvcsupabase.dto.Respuestas;
import com.pkg.spring.msvc.msvcsupabase.security.dto.JwtDto;
import com.pkg.spring.msvc.msvcsupabase.security.dto.LoginUsuario;
import com.pkg.spring.msvc.msvcsupabase.security.dto.NewUser;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Rol;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Usuario;
import com.pkg.spring.msvc.msvcsupabase.security.enums.RolNombre;
import com.pkg.spring.msvc.msvcsupabase.security.jwt.JwtProvider;
import com.pkg.spring.msvc.msvcsupabase.security.service.RolService;
import com.pkg.spring.msvc.msvcsupabase.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @RequestMapping("/nuevo")
    public ResponseEntity<Respuestas> nuervo(@Valid @RequestBody NewUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuestas(bindingResult.getFieldErrors(),"Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST));
        if (usuarioService.existsByNombreUsuario(user.getNombreUsuario()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuestas("Usuario ya existe", HttpStatus.BAD_REQUEST));
        if (usuarioService.existsByEmail(user.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuestas("El email ya existe", HttpStatus.BAD_REQUEST));
        Usuario usuario =
                new Usuario(user.getNombre(), user.getNombreUsuario(), user.getEmail(),
                        passwordEncoder.encode(user.getPassword()));
        Set<Rol> roles =  new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(user.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return ResponseEntity.ok(new Respuestas(usuario,"Usuario Creado",HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<Respuestas> login (@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuestas("Campos mal puestos",HttpStatus.BAD_REQUEST));

        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                                                                              loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =  jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok().body(new Respuestas(jwtDto,"Token generado con exitos",HttpStatus.OK));
    }
}
