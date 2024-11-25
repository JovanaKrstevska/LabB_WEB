package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    public static List<Album> albumList = new ArrayList<>();

    @PostConstruct
    public void init(){
        artistList.add(new Artist(1L, "Freddie", "Mercury", "Legendary rock singer and songwriter, lead vocalist of Queen."));
        artistList.add(new Artist(2L, "Aretha", "Franklin", "The Queen of Soul, known for her powerful voice and gospel roots."));
        artistList.add(new Artist(3L, "Miles", "Davis", "Influential jazz trumpeter and composer, pioneer of modern jazz."));
        artistList.add(new Artist(4L, "Beyonce", "Knowles", "Iconic pop and R&B singer, known for her dynamic performances."));
        artistList.add(new Artist(5L, "Wolfgang Amadeus", "Mozart", "Classical composer, one of the most prolific and influential musicians of his time."));

        albumList.add(new Album(1L, "Thriller", "Pop", "1982"));
        albumList.add(new Album(2L, "Back in Black", "Rock", "1980"));
        albumList.add(new Album(3L, "The Dark Side of the Moon", "Progressive Rock", "1973"));
        albumList.add(new Album(4L, "Rumours", "Rock", "1977"));
        albumList.add(new Album(5L, "Abbey Road", "Rock", "1969"));

        songList.add(new Song(1L, "Bohemian Rhapsody", "Rock", 1975, albumList.get(0)));
        songList.add(new Song(2L, "Respect", "Soul", 1967, albumList.get(1)));
        songList.add(new Song(3L, "So What", "Jazz", 1959, albumList.get(2)));
        songList.add(new Song(4L, "Single Ladies", "Pop", 2008, albumList.get(3)));
        songList.add(new Song(5L, "Symphony No. 40", "Classical", 1788, albumList.get(4)));
    }
}
