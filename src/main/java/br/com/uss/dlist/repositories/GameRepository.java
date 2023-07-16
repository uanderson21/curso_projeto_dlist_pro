package br.com.uss.dlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import br.com.uss.dlist.entities.Game;
import br.com.uss.dlist.projections.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	//nativeQuery = true -> para funcionar a consulta SQL e não JPQL
	// quando se usa um SQL é necessario ter uma projecao, ou seja, criar uma interface para customizar a consulta SQL
	@Query(nativeQuery = true, value = """
			SELECT tb_game.id, tb_game.title, tb_game.game_year AS `year`, tb_game.img_url AS imgUrl,
			tb_game.short_description AS shortDescription, tb_belonging.position
			FROM tb_game
			INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
			WHERE tb_belonging.list_id = :listId
			ORDER BY tb_belonging.position
				""")
	List<GameMinProjection> searchByList(Long listId);	

}
