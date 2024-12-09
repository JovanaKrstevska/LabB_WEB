package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Long songId, Long artistId);
    Optional<Song> findByTrackId(Long trackId);
    Optional<Song> saveSong(String title, String genre, int releaseYear, Album album);
    void deleteById(Long id);
    List<Song> getSongsByAlbumId(Long albumId);
    Song update(String title, String genre, int releaseYear, Album album);
}
