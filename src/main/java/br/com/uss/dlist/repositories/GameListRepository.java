package br.com.uss.dlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uss.dlist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
	

}
