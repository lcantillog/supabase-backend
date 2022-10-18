package com.pkg.spring.msvc.msvcsupabase.controller;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.dto.Respuestas;
import com.pkg.spring.msvc.msvcsupabase.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/favorito")
@CrossOrigin(origins = "*")
public class FavoritoController {

    @Autowired
    FavoritoService favoritoService;

    @PostMapping("/nuevo")
    public ResponseEntity<Respuestas> nuervo(@Valid @RequestBody ChisteDto chiste, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Respuestas(bindingResult.getFieldErrors(), "Propiedades invalidas", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

        var item = favoritoService.saveFavorito(chiste);

        return new ResponseEntity(new Respuestas(item.get(), "Registro guardado exitosamente", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Respuestas> getAllUsuario() {
        var list = favoritoService.getListFavorito();
        return ResponseEntity.status(HttpStatus.OK).body(new Respuestas(list.get(), "Registro obtenidos exitosamente", HttpStatus.OK));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Respuestas> deleteFavorito(@PathVariable("id") String id) {
        var favorito = favoritoService.deleteFavorito(id);
        if (!favorito.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(new Respuestas(favorito.get(),
                "Registro eliminado exitosamente",
                HttpStatus.OK));
    }


}
