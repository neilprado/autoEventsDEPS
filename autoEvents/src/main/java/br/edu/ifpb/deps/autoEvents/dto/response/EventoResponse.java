package br.edu.ifpb.deps.autoEvents.dto.response;

import br.edu.ifpb.deps.autoEvents.model.Evento;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class EventoResponse {
    private Long id;
    private String nome;
    private String cidade;
    private String pais;
    private LocalDateTime dataEvento;
    private double valorIngresso;

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }



    public static EventoResponse from(Evento evento){
        EventoResponse eventoResponse = new EventoResponse();
        eventoResponse.setCidade(evento.getCidade());
        eventoResponse.setDataEvento(evento.getDataEvento());
        eventoResponse.setId(evento.getId());
        eventoResponse.setNome(evento.getNome());
        eventoResponse.setPais(evento.getPais());
        eventoResponse.setValorIngresso(evento.getValorIngresso());

        return eventoResponse;
    }

    public static Page<EventoResponse> from(Page<Evento> eventos){
        Page<EventoResponse> eventoResponses = eventos.map(evento -> {
            EventoResponse eventoResponse = new EventoResponse();
            eventoResponse.setCidade(evento.getCidade());
            eventoResponse.setDataEvento(evento.getDataEvento());
            eventoResponse.setId(evento.getId());
            eventoResponse.setNome(evento.getNome());
            eventoResponse.setPais(evento.getPais());
            eventoResponse.setValorIngresso(evento.getValorIngresso());

            return eventoResponse;
        });
    return eventoResponses;
    }
}
