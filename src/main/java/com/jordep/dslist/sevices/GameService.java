package com.jordep.dslist.sevices;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jordep.dslist.dto.GameDTO;
import com.jordep.dslist.dto.GameMinDTO;
import com.jordep.dslist.entities.Game;
import com.jordep.dslist.repositories.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository; // Service injeta um Repository || Controller -> Service -> Repository

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).orElseThrow(); // .get() pode gerar erro caso o id não exista, para que isso não ocorra poderia fazer um tratamento de exceção utilizando .orElseThrow()
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        return gameRepository.findAll().stream()
                .map(GameMinDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) { // Recebe o Id da lista e retonar os games da lista (GameMinDTO)
        return gameRepository.searchByList(listId).stream()
                .map(GameMinDTO::new)
                .toList();
    }

}
