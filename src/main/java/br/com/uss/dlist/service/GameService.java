package br.com.uss.dlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uss.dlist.dto.GameDTO;
import br.com.uss.dlist.dto.GameMinDTO;
import br.com.uss.dlist.entities.Game;
import br.com.uss.dlist.projections.GameMinProjection;
import br.com.uss.dlist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;	
	
	
	/**
	 * @author udsantos
	 * Retorna um objeto GameDTO correspondente ao ID fornecido.
	 *
	 * @param id O ID do jogo a ser encontrado.
	 * @return Objeto GameDTO correspondente ao ID fornecido.
	 */
	@Transactional(readOnly = true)
	public GameDTO findById(Long id){
		Game result = repository.findById(id).get();		
		GameDTO dto = new GameDTO(result);
		return dto;
	}
	
	/**
	 * @author udsantos
	 * Retorna uma lista de objeto GameMinDTO com os dados minimos.
	 *
	 * @return Objeto GameMinDTO.
	 */	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> result = repository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result = repository.searchByList(listId);
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
	
	
}
