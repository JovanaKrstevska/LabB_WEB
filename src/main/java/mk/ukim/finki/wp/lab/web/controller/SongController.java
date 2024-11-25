package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.exception.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        List<Song> songList = songService.listSongs();
        model.addAttribute("songList", songList);

        if (error != null) {
            model.addAttribute("error", error);
        }
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String addSongForm(Model model) {
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.listAlbums();
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";

    }

    @PostMapping("/add")
    public String saveSong(@RequestParam(required = false) String title, @RequestParam(required = false) Long trackId, @RequestParam(required = false) String genre, @RequestParam(required = false) int releaseYear, @RequestParam(required = false) Long albumId) {
        Album album = albumService.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));

        if (trackId == null) {
            this.songService.saveSong(title, genre, releaseYear, album);
            return "redirect:/songs";
        }

        Song song = this.songService.findByTrackId(trackId).orElseThrow(() -> new SongNotFoundException(trackId));
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumService.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId)));
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        Song song = this.songService.findByTrackId(id).orElseThrow(() -> new SongNotFoundException(id));
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.listAlbums();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId) {
        Song song = this.songService.findByTrackId(songId).orElseThrow(() -> new SongNotFoundException(songId));
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumService.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId)));
        return "redirect:/songs";
    }
}