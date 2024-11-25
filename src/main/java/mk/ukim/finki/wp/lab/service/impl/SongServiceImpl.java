package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exception.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Optional<Song> findByTrackId(Long trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> saveSong(String title, String genre, int releaseYear, Album album) {
        return songRepository.save(title, genre, releaseYear, album);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public void addArtistToSong(Song song, Artist artist) {
        Optional<Song> existingSong = DataHolder.songList.stream()
                .filter(s -> Objects.equals(s.getTitle(), song.getTitle()))
                .findFirst();

        if (existingSong.isPresent()) {
            Song foundSong = existingSong.get();
            if (!foundSong.getPerformers().contains(artist)) {
                foundSong.getPerformers().add(artist);
            }
            songRepository.save(foundSong.getTitle(), foundSong.getGenre(), foundSong.getReleaseYear(), foundSong.getAlbum());
        } else {
            throw new IllegalArgumentException("Song not found: " + song.getTitle());
        }
    }

}
