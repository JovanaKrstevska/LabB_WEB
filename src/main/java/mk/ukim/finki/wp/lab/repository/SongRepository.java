package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songList;
    }

    public Optional<Song> findByTrackId(Long trackId){
        return DataHolder.songList.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song){
        Song pesna = DataHolder.songList.stream().filter(s -> s.getTrackId().equals(song.getTrackId())).findFirst().orElseThrow(() -> new SongNotFoundException(song.getTrackId()));
        pesna.getPerformers().add(artist);
        return artist;
    }

    public Optional<Song> save(String title, String genre, Integer releaseYear, Album album){
        Song song = new Song(title, genre, releaseYear, album);
        DataHolder.songList.removeIf(s -> Objects.equals(s.getTitle(), title));
        DataHolder.songList.add(song);
        return Optional.of(song);
    }

    public void deleteById(Long id) {
        DataHolder.songList.removeIf(s -> s.getTrackId().equals(id));
    }
}
