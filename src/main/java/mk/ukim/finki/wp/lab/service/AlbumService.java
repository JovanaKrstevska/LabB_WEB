package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> listAlbums();
    Optional<Album> findById(Long id);
}
