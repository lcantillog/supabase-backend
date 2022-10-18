package com.pkg.spring.msvc.msvcsupabase.service;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.entity.Favorito;
import com.pkg.spring.msvc.msvcsupabase.interfaz.FavoritoInterfaz;
import com.pkg.spring.msvc.msvcsupabase.repository.FavoritoRepository;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Usuario;
import com.pkg.spring.msvc.msvcsupabase.security.entity.UsuarioPrincipal;
import com.pkg.spring.msvc.msvcsupabase.security.service.UsuarioService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class FavoritoService implements FavoritoInterfaz {

    @Autowired
    FavoritoRepository favoritoRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public Optional<ChisteDto> saveFavorito(ChisteDto chisteDto) {
        var user = this.getUsuarioAuth();
        Favorito favorito;
        if (favoritoRepository.existsByUsuarioAndIdChuck(user.get(), chisteDto.getIdChuckNorris())) {
            var find =  favoritoRepository.findByUsuarioAndIdChuck(user.get(), chisteDto.getIdChuckNorris());
            favorito = find.get();
        } else {
            favorito = Favorito.builder()
                    .idChuck(chisteDto.getIdChuckNorris())
                    .url(chisteDto.getUrlChuckNorris())
                    .icono(chisteDto.getIconoUrl())
                    .valor(chisteDto.getValorChuckNorris())
                    .usuario(user.get())
                    .build();
        }
        favoritoRepository.save(favorito);
        return Optional.of(chisteDto);
    }

    @Override
    public Optional<List<ChisteDto>> getListFavorito() {
        var user = this.getUsuarioAuth();
        var list = favoritoRepository.findByUsuario(user.get());
        List<ChisteDto> listDto = new ArrayList<>();
        for (val item : list.get()) {
            listDto.add(ChisteDto.builder()
                    .idChuckNorris(item.getIdChuck())
                    .valorChuckNorris(item.getValor())
                    .urlChuckNorris(item.getUrl())
                    .iconoUrl(item.getIcono())
                    .build());
        }

        return Optional.of(listDto);
    }

    @Override
    public Optional<ChisteDto> deleteFavorito(String id) {
        var userAuth = this.getUsuarioAuth();
        var favorito = favoritoRepository.findByUsuarioAndIdChuck(userAuth.get(), id);
        favoritoRepository.delete(favorito.get());
        return Optional.of(ChisteDto.builder()
                .iconoUrl(favorito.get().getIcono())
                .urlChuckNorris(favorito.get().getUrl())
                .valorChuckNorris(favorito.get().getValor())
                .idChuckNorris(favorito.get().getIdChuck()).build());
    }

    /**
     * Obtiene el usuario logeado
     *
     * @return Optional<Usuario>
     */
    private Optional<Usuario> getUsuarioAuth() {
        Authentication auth = getContext().getAuthentication();
        AtomicReference<UsuarioPrincipal> usuarioPrincipal = new AtomicReference<>((UsuarioPrincipal) auth.getPrincipal());
        Optional<Usuario> user = usuarioService.getByNombreUsuario(usuarioPrincipal.get().getNombreUsuario());
        return user;
    }
}
