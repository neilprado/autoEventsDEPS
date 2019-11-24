package br.edu.ifpb.deps.autoEvents.service;

import br.edu.ifpb.deps.autoEvents.dto.request.UsuarioRequest;
import br.edu.ifpb.deps.autoEvents.model.Evento;
import br.edu.ifpb.deps.autoEvents.model.Usuario;
import br.edu.ifpb.deps.autoEvents.repository.EventoRepository;
import br.edu.ifpb.deps.autoEvents.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository repository;
    private EventoRepository eventoRepository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(UsuarioRequest request){
        Usuario usuario = new Usuario();

        if(this.repository.findByEmail(request.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado na base de dados");
        }else{
            usuario.setEmail(request.getEmail());
        }
        if(request.getDataNascimento().compareTo(LocalDate.now()) > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossível cadastrar data de nascimento maior que a atual");
        }else{
            usuario.setDataNascimento(request.getDataNascimento());
        }
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());

        return this.repository.save(usuario);
    }

    public Page<Usuario> listarUsuario(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public Usuario buscarUsuario(Long id){
        Usuario usuario = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return usuario;
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequest request){
        Usuario usuario = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        if(request.getDataNascimento().compareTo(LocalDate.now()) > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossível cadastrar data de nascimento maior que a atual");
        }else{
            usuario.setDataNascimento(request.getDataNascimento());
        }

        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());
        usuario.setEmail(request.getEmail());

        return this.repository.save(usuario);
    }

    public void removerUsuario(Long id){
        Usuario usuario = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        this.repository.delete(usuario);
    }

    public List<Evento> comprarIngresso(Long id, List<Long> eventosId){
        List<Evento> eventos = new ArrayList<>();
        Usuario usuario = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        for(Long idEvento: eventosId){
            Evento evento = this.eventoRepository.findById(idEvento).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
            eventos.add(evento);
        }
        return eventos;
    }
}
