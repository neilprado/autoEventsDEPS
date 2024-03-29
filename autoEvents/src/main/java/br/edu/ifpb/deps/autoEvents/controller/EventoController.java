package br.edu.ifpb.deps.autoEvents.controller;

import br.edu.ifpb.deps.autoEvents.dto.request.EventoRequest;
import br.edu.ifpb.deps.autoEvents.dto.response.EventoResponse;
import br.edu.ifpb.deps.autoEvents.model.Evento;
import br.edu.ifpb.deps.autoEvents.service.EventoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class EventoController {
    private EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventoResponse> cadastrarEvento(@Valid @RequestBody EventoRequest request){
        Evento evento = this.service.cadastrarEvento(request);
        return ResponseEntity.ok(EventoResponse.from(evento));
    }

    @GetMapping
    public ResponseEntity<Page<EventoResponse>> listarEvento(Pageable pageable){
        Page<Evento> eventos = this.service.listarEventos(pageable);
        return ResponseEntity.ok(EventoResponse.from(eventos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarEvento(@Valid @PathVariable(value = "id") Long id){
        Evento evento = this.service.buscarEvento(id);
        return ResponseEntity.ok(EventoResponse.from(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> atualizarEvento(@Valid @PathVariable(value = "id") Long id,
                                                          @Valid @RequestBody EventoRequest request){
        Evento evento = this.service.atualizarEvento(id, request);
        return ResponseEntity.ok(EventoResponse.from(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEvento(@Valid @PathVariable(value = "id") Long id){
        this.service.removerEvento(id);
        return ResponseEntity.ok().build();
    }
}
