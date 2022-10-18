package com.pkg.spring.msvc.msvcsupabase.repository;

import com.pkg.spring.msvc.msvcsupabase.entity.Favorito;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    Optional<List<Favorito>> findByUsuario(Usuario usuario);
    Optional<Favorito> findByUsuarioAndIdChuck(Usuario usuario, String idChuck);
    boolean existsByUsuarioAndIdChuck(Usuario usuario, String idChuck);
}
