package com.pkg.spring.msvc.msvcsupabase.interfaz;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.dto.RandomDto;

import java.util.Optional;

public interface RandomInterfaz {
    Optional<RandomDto> getChisteApi();
    Optional<ChisteDto> getChisteChuckNorris();
}
