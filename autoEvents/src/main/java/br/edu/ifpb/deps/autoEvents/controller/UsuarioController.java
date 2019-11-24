package br.edu.ifpb.deps.autoEvents.controller;

import br.edu.ifpb.deps.autoEvents.dto.request.UsuarioRequest;
import br.edu.ifpb.deps.autoEvents.dto.response.UsuarioResponse;
import br.edu.ifpb.deps.autoEvents.model.Usuario;
import br.edu.ifpb.deps.autoEvents.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest request){
        Usuario usuario = this.service.cadastrarUsuario(request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listarUsuarios(Pageable pageable){
        Page<Usuario> usuarios = this.service.listarUsuario(pageable);
        return ResponseEntity.ok(UsuarioResponse.from(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@Valid @PathVariable("id") Long id){
        Usuario usuario = this.service.buscarUsuario(id);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@Valid @PathVariable("id") Long id,
                                                            @Valid @RequestBody UsuarioRequest request){
        Usuario usuario = this.service.atualizarUsuario(id, request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@Valid @PathVariable("id") Long id){
        this.service.removerUsuario(id);
        return ResponseEntity.ok().build();
    }
}
