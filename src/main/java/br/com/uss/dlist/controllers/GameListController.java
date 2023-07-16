package br.com.uss.dlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uss.dlist.dto.GameListDTO;
import br.com.uss.dlist.service.GameListService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	@Autowired
	private GameListService service;
	
	@GetMapping
	public List<GameListDTO> findAll(){
		return service.findAll();
	}	
	
}
