package org.thatmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.model.PlayList;
import org.thatmovie.service.PlayListService;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlayListController {
    @Autowired
    PlayListService playListService;

    /**
     * Obtiene todas las listas de reproducción
     *
     * @return Lista de listas de reproducción
     */
    @GetMapping
    public ResponseEntity<List<PlayList>> getAllPlayList(){
        List<PlayList> list = playListService.getAllPlayList();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene una lista de reproducción por su id
     *
     * @param id El id de la lista de reproducción
     * @return La lista de reproducción con el id dado
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable("id") int id){
        PlayList list = playListService.getPlayListById(id);
        return ResponseEntity.ok(list);
    }

    /**
     * Crea una nueva lista de reproducción
     *
     * @param playList La lista de reproducción a crear
     * @return La lista de reproducción creada
     */
    @PostMapping
    public ResponseEntity<PlayList> createPlayList(@RequestBody PlayList playList){
        PlayList list = playListService.createPlayList(playList);
        return ResponseEntity.ok(list);
    }

    /**
     * Elimina una lista de reproducción por su id
     *
     * @param id El id de la lista de reproducción a eliminar
     */
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable("id") int id){
        playListService.deletePlayList(id);
    }
}