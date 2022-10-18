package com.pkg.spring.msvc.msvcsupabase.controller;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.dto.Respuestas;
import com.pkg.spring.msvc.msvcsupabase.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/random")
@CrossOrigin(origins = "*")
public class RandomController {

    @Autowired
    RandomService randomService;

    @GetMapping("/chiste-chuck")
    public ResponseEntity<Respuestas> getChisteChuckNorris(){
        Optional<ChisteDto> random = randomService.getChisteChuckNorris();
        return ResponseEntity.ok().body(new Respuestas(random.get(),"Chistes obtenidos exitosamente", HttpStatus.OK));
    }
}
