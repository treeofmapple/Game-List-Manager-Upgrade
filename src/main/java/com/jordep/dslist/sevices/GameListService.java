package com.jordep.dslist.sevices;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jordep.dslist.dto.GameListDTO;
import com.jordep.dslist.projections.GameMinProjection;
import com.jordep.dslist.repositories.GameListRepository;
import com.jordep.dslist.repositories.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameListService {

	private final GameRepository gameRepository;
    private final GameListRepository listRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return listRepository.findAll().stream()
                .map(GameListDTO::new)
                .toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);
        
        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);
//                     1               3               if             else
        // interar sobre a lista (modificada) e atualizar a posição no DB
        for (int i = min; i <= max; i++) {
            listRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }
}

//         EXEMPLO
// [
//        { id: 1, title: "Game A" }, // Índice 0
//        { id: 2, title: "Game B" }, // Índice 1           sourceIndex
//        { id: 3, title: "Game C" }, // Índice 2
//        { id: 4, title: "Game D" }  // Índice 3           destinationIndex
// ]
//     list.remove(sourceIndex);
// [
//        { id: 1, title: "Game A" }, // Índice 0
//        { id: 3, title: "Game C" }, // Índice 1
//        { id: 4, title: "Game D" }  // Índice 2
// ]
//     list.add(destinationIndex, obj);
// [
//        { id: 1, title: "Game A" }, // Índice 0
//        { id: 3, title: "Game C" }, // Índice 1
//        { id: 4, title: "Game D" }  // Índice 2
//        { id: 2, title: "Game B" }, // Índice 3
// ]