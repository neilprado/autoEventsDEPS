package br.edu.ifpb.deps.autoEvents.repository;

import br.edu.ifpb.deps.autoEvents.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
