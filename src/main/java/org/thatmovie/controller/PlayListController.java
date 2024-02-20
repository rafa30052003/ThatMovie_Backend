package org.thatmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.model.Movie;
import org.thatmovie.model.DTO.MovieDTO;
import org.thatmovie.model.PlayList;
import org.thatmovie.service.PlayListService;

import java.util.List;
@CrossOrigin(origins = "*")
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




   /**
     +     * Agrega una película a una lista de reproducción.
     +     *
     +     * @param  id    el ID de la lista de reproducción
     +     * @param  movie la película que se va a añadir
     +     * @return       una respuesta que indica el éxito o fracaso de la operación
     +     */
    @PostMapping("/playlist/{id}/addMovie")
    public ResponseEntity<?> addMovieToPlayList(@PathVariable("id") int id, @RequestBody MovieDTO movie) {
        try {
            PlayList playList = playListService.getPlayListById(id);
            if (playList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found");
            }
            Movie movieToBeStore = new Movie();
            movieToBeStore.setAdult(movie.isAdult());
            movieToBeStore.setBackdrop_path(movie.getBackdrop_path());
            //movieToBeStore.setGenres(movie.getGenres());
            movieToBeStore.setId(movie.getId());
            movieToBeStore.setOriginal_language(movie.getOriginal_language());
            movieToBeStore.setOriginal_title(movie.getOriginal_title());
            movieToBeStore.setOverview(movie.getOverview());
            movieToBeStore.setPopularity(movie.getPopularity());
            movieToBeStore.setPoster_path(movie.getPoster_path());
            movieToBeStore.setRelease_date(movie.getRelease_date());
            movieToBeStore.setTitle(movie.getTitle());
            movieToBeStore.setVideo(movie.isVideo());
            movieToBeStore.addToPlayList(playList);
            playList.addMovie(movieToBeStore);
            playListService.createPlayList(playList);
            return ResponseEntity.ok("Movie added to playlist successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding movie to playlist: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}/posters")
    public ResponseEntity<List<String>> getPostersInPlayList(@PathVariable("id") Integer id) {
        List<String> posters = playListService.getMoviePostersInPlaylist(id);
        return ResponseEntity.ok(posters);
    }


}