package br.com.uss.dlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uss.dlist.dto.GameListDTO;
import br.com.uss.dlist.dto.GameMinDTO;
import br.com.uss.dlist.dto.ReplacementDTO;
import br.com.uss.dlist.service.GameListService;
import br.com.uss.dlist.service.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	@Autowired
	private GameListService service;
	
	@Autowired
	private GameService serviceGame;
	
	@GetMapping
	public List<GameListDTO> findAll(){
		return service.findAll();
	}	
	
	@GetMapping("/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId){
		return serviceGame.findByList(listId);
	}	
	
	@PostMapping("/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO dto){
		service.move(listId, dto.getSourceIndex(), dto.getDestinationIndex());
	}	
	
}
