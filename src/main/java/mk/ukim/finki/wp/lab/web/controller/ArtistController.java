package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/add-to-song")
    public String showAddArtistForm(@RequestParam(required = true) Long songId, Model model) {
        Song song = songService.findByTrackId(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        return "addArtistToSong";
    }

    @PostMapping("/add-to-song")
    public String addArtistToSong(@RequestParam Long songId, @RequestParam Long artistId) {
        songService.addArtistToSong(songId, artistId);
        return "redirect:/songs";
    }
}
