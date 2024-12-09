package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepositoryImpl {
    public List<Artist> findAll() {
        return DataHolder.artistList;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artistList.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

    public Song addSongsToArtist(Artist artist, Song song){
        Artist artists = DataHolder.artistList.stream().filter(a -> a.getId().equals(artist.getId())).findFirst().orElseThrow(() -> new ArtistNotFoundException(artist.getId()));
        return song;
    }
}
