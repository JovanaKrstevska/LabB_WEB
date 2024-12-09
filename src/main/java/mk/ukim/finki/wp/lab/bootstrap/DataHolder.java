package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    public static List<Album> albumList = new ArrayList<>();

    public DataHolder(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    public void init(){
        artistList.add(new Artist( "Freddie", "Mercury", "Legendary rock singer and songwriter, lead vocalist of Queen."));
        artistList.add(new Artist( "Aretha", "Franklin", "The Queen of Soul, known for her powerful voice and gospel roots."));
        artistList.add(new Artist("Miles", "Davis", "Influential jazz trumpeter and composer, pioneer of modern jazz."));
        artistList.add(new Artist( "Beyonce", "Knowles", "Iconic pop and R&B singer, known for her dynamic performances."));
        artistList.add(new Artist("Wolfgang Amadeus", "Mozart", "Classical composer, one of the most prolific and influential musicians of his time."));

        albumList.add(new Album("Thriller", "Pop", "1982"));
        albumList.add(new Album("Back in Black", "Rock", "1980"));
        albumList.add(new Album("The Dark Side of the Moon", "Progressive Rock", "1973"));
        albumList.add(new Album("Rumours", "Rock", "1977"));
        albumList.add(new Album( "Abbey Road", "Rock", "1969"));

        songList.add(new Song( "Bohemian Rhapsody", "Rock", 1975, albumList.get(0)));
        songList.add(new Song( "Respect", "Soul", 1967, albumList.get(1)));
        songList.add(new Song( "So What", "Jazz", 1959, albumList.get(2)));
        songList.add(new Song("Single Ladies", "Pop", 2008, albumList.get(3)));
        songList.add(new Song( "Symphony No. 40", "Classical", 1788, albumList.get(4)));

        albumRepository.saveAll(albumList);
        songRepository.saveAll(songList);
    }
}
