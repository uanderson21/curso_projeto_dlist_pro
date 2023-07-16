package br.com.uss.dlist.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uss.dlist.dto.GameListDTO;
import br.com.uss.dlist.entities.GameList;
import br.com.uss.dlist.projections.GameMinProjection;
import br.com.uss.dlist.repositories.GameListRepository;
import br.com.uss.dlist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Autowired
	private GameRepository gameRepository;	
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> result = repository.findAll();
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;
	}

	/*
	 * Metodo que reorganiza o posicionamento da lista 
	 * 
	 */
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);		
		
		//GameMinProjection obj = list.remove(sourceIndex);
		
		//list.add(destinationIndex, obj);
		
		//int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		//int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		//for (int i = min; i <= max; i++) {
		//repository.updateBelongingPosition(listId, list.get(i).getId(), i);
		//}
		
		
	    if (sourceIndex < 0 || sourceIndex >= list.size() || destinationIndex < 0 || destinationIndex >= list.size()) {
	        throw new IndexOutOfBoundsException("Invalid source or destination index");
	    }

	    GameMinProjection game = list.remove(sourceIndex);
	    list.add(destinationIndex, game);

	    IntStream.rangeClosed(Math.min(sourceIndex, destinationIndex), Math.max(sourceIndex, destinationIndex))
	            .forEach(i -> {
	                GameMinProjection gameToUpdate = list.get(i);
	                repository.updateBelongingPosition(listId, gameToUpdate.getId(), i);
	            });
		}
	
}
