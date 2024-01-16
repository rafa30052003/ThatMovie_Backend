package org.thatmovie.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.exception.RecordNotFoundException;
import org.thatmovie.model.PlayList;
import org.thatmovie.repository.PlayListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayListService {
    @Autowired
    PlayListRepository playListRepo;

    public List<PlayList> getAllPlayList() {
        List<PlayList> playLists = playListRepo.findAll();
        return playLists;
    }

    public PlayList getPlayListById(int id) {
        Optional<PlayList> playList = playListRepo.findById(id);
        if (playList.isPresent()) {
            return playList.get();
        } else {
            throw new RecordNotFoundException("No playlist found with id: " + id);
        }

    }


    public PlayList createPlayList(PlayList playList) {
        PlayList end;
        if (playList.getId() != -1) {//update
            Optional<PlayList> result = playListRepo.findById(playList.getId());
            if (result.isPresent()) {
                PlayList fromDB = result.get();
                fromDB.setName(playList.getName());
                fromDB.setLike(playList.getLike());
                end = playListRepo.save(fromDB);
            } else {
                throw new RecordNotFoundException("No playlist found with id: " + playList.getId());
            }
        } else {//inset
            end=playListRepo.save(playList);
        }
        return end;
    }

    public void deletePlayList(int id) {
        Optional<PlayList> result = playListRepo.findById(id);
        if (result.isPresent()) {
            playListRepo.deleteById(id);
        } else {
            throw new RecordNotFoundException("No playlist found with id: " + id);
        }
    }

}
