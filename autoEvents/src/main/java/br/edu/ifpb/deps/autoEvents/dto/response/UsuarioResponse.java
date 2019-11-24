package br.edu.ifpb.deps.autoEvents.dto.response;

import br.edu.ifpb.deps.autoEvents.model.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static UsuarioResponse from(Usuario usuario){
        UsuarioResponse response = new UsuarioResponse();

        response.setDataNascimento(usuario.getDataNascimento());
        response.setEmail(usuario.getEmail());
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setSenha(usuario.getSenha());

        return response;
    }

    public static Page<UsuarioResponse> from (Page<Usuario> usuarios){
        Page<UsuarioResponse> responses = usuarios.map(usuario -> {
            UsuarioResponse response = new UsuarioResponse();

            response.setDataNascimento(usuario.getDataNascimento());
            response.setEmail(usuario.getEmail());
            response.setId(usuario.getId());
            response.setNome(usuario.getNome());
            response.setSenha(usuario.getSenha());

            return response;
        });

        return responses;
    }
}
