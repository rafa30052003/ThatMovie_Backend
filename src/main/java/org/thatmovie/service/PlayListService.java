package org.thatmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.exception.RecordNotFoundException;
import org.thatmovie.model.Member;
import org.thatmovie.model.Movie;
import org.thatmovie.model.PlayList;
import org.thatmovie.repository.PlayListRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListService {
    @Autowired
    PlayListRepository playListRepo;


    /**
     * Obtiene todas las listas de reproducción
     *
     * @return Lista de listas de reproducción
     */
    public List<PlayList> getAllPlayList() {
        List<PlayList> playLists2 = playListRepo.findAll();

        return playLists2;

    }

    /**
     * Obtiene una lista de reproducción por su id
     *
     * @param id El id de la lista de reproducción
     * @return La lista de reproducción con el id dado
     */
    public PlayList getPlayListById(int id) {
        Optional<PlayList> playList = playListRepo.findById(id);
        if (playList.isPresent()) {
            return playList.get();
        } else {
            throw new RecordNotFoundException("No se encontró una lista de reproducción con el id: " + id);
        }
    }

    /**
     * Crea o actualiza una lista de reproducción
     *
     * @param playList La lista de reproducción a crear o actualizar
     * @return La lista de reproducción creada o actualizada
     */
    public PlayList createPlayList(PlayList playList) {
        PlayList end;
        if (playList.getId() > 0) {
            Optional<PlayList> result = playListRepo.findById(playList.getId());
            if (result.isPresent()) {
                PlayList fromDB = result.get();
                fromDB.setName(playList.getName());
                fromDB.setLike(playList.getLike());


                end = playListRepo.save(fromDB);
            } else {
                throw new RecordNotFoundException("No se encontró una lista de reproducción con el id: " + playList.getId());
            }
        } else {
            end = playListRepo.save(playList);
        }
        return end;
    }

    /**
     * Elimina una lista de reproducción por su id
     *
     * @param id El id de la lista de reproducción a eliminar
     */
    public void deletePlayList(int id) {
        Optional<PlayList> result = playListRepo.findById(id);
        if (result.isPresent()) {
            playListRepo.deleteById(id);
        } else {
            throw new RecordNotFoundException("No se encontró una lista de reproducción con el id: " + id);
        }
    }

    public List<String> getMoviePostersInPlaylist(Integer idPlayList) {
        List<String> posters = new ArrayList<>();

        PlayList playlist = getPlayListById(idPlayList);
        List<Movie> moviesInPlaylist = playlist.getMovies();

        for (Movie movie : moviesInPlaylist) {
            String poster = movie.getPoster_path();
            posters.add(poster);
        }

        return posters;
    }


}