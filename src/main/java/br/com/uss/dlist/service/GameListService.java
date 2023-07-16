package br.com.uss.dlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uss.dlist.dto.GameListDTO;
import br.com.uss.dlist.entities.GameList;
import br.com.uss.dlist.repositories.GameListRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> result = repository.findAll();
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;
	}
	
}
