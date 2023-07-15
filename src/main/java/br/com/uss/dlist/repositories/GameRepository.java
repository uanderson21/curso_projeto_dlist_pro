package br.com.uss.dlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uss.dlist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
