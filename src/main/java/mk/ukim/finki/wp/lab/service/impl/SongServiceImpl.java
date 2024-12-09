package mk.ukim.finki.wp.lab.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.impl.AlbumRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.impl.ArtistRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.impl.SongRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final ArtistRepositoryImpl artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(ArtistRepositoryImpl artistRepository, SongRepository songRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Long songId, Long artistId) {
        return songRepository.addArtistToSong(songId, artistId);
    }

    @Override
    public Optional<Song> findByTrackId(Long trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Transactional
    @Override
    public Optional<Song> saveSong(String title, String genre, int releaseYear, Album album) {
        Song song = new Song(title, genre, releaseYear, album);
       return Optional.of(songRepository.save(song));
    }
    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public List<Song> getSongsByAlbumId(Long albumId) {
        List<Song> songs = songRepository.findAllByAlbum_Id(albumId);
        return songs;
    }

    @Override
    public Song update(String title, String genre, int releaseYear, Album album) {
        Song song = new Song(title, genre, releaseYear, album);
        songRepository.save(song);
        return song;
    }
}
