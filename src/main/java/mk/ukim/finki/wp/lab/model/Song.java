package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackId;
    private String title;
    private String genre;
    private int releaseYear;

    @ManyToMany
    private List<Artist> performers;

    @ManyToOne
    private Album album;

//    public Song(Long trackId, String title, String genre, int releaseYear, Album album) {
//        this.title = title;
//        this.genre = genre;
//        this.releaseYear = releaseYear;
//        performers = new ArrayList<>();
//        this.album = album;
//    }
    public Song(String title, String genre, int releaseYear, Album album){
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
    }
}
