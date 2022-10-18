package com.pkg.spring.msvc.msvcsupabase.security.repository;

import com.pkg.spring.msvc.msvcsupabase.security.entity.Rol;
import com.pkg.spring.msvc.msvcsupabase.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
