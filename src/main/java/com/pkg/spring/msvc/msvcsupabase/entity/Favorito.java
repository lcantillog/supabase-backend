package com.pkg.spring.msvc.msvcsupabase.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pkg.spring.msvc.msvcsupabase.security.entity.Usuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Favorito {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull(message = "El idChuck no pueder ser vacia")
  private String idChuck;
  @NotNull(message = "La url del icono no pueder ser vacia")
  private String icono;
  @NotNull(message = "La url no pueder ser vacia")
  private String url;
  @NotNull(message = "El valor no pueder ser vacia")
  private String valor;

  @NotNull(message = "EL ususario no puede ser vacio")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Usuario usuario;

 }
