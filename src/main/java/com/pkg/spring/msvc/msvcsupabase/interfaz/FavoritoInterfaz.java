package com.pkg.spring.msvc.msvcsupabase.interfaz;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface FavoritoInterfaz {

    Optional<ChisteDto> saveFavorito(ChisteDto chisteDto);
    Optional<List<ChisteDto>> getListFavorito();
    Optional<ChisteDto> deleteFavorito(String id);

}
