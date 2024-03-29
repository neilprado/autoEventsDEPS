package br.edu.ifpb.deps.autoEvents.service;

import br.edu.ifpb.deps.autoEvents.dto.request.EventoRequest;
import br.edu.ifpb.deps.autoEvents.model.Evento;
import br.edu.ifpb.deps.autoEvents.model.Usuario;
import br.edu.ifpb.deps.autoEvents.repository.EventoRepository;
import br.edu.ifpb.deps.autoEvents.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class EventoService {
    private EventoRepository repository;
    private UsuarioRepository usuarioRepository;

    public EventoService(EventoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Evento cadastrarEvento(EventoRequest request){
        Evento evento = new Evento();

        Usuario usuario = this.usuarioRepository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        evento.setNome(request.getNome());
        evento.setCidade(request.getCidade());
        evento.setDataEvento(request.getDataEvento());
        evento.setPais(request.getPais());
        evento.setUsuario(usuario);
        evento.setValorIngresso(request.getValorIngresso());

        return this.repository.save(evento);
    }

    public Page<Evento> listarEventos(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public Evento buscarEvento(Long id){
        Evento evento = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));

        return evento;
    }

    public Evento atualizarEvento(Long id, EventoRequest request){
        Evento evento = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));

        Usuario usuario = this.usuarioRepository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        evento.setNome(request.getNome());
        evento.setCidade(request.getCidade());
        evento.setDataEvento(request.getDataEvento());
        evento.setPais(request.getPais());
        evento.setUsuario(usuario);
        evento.setValorIngresso(request.getValorIngresso());

        return this.repository.save(evento);
    }

    public void removerEvento(Long id){
        Evento evento = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
        this.repository.delete(evento);
    }
}
