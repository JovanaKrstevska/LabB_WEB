package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.exception.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ArtistDetailServlet", urlPatterns = "/artist/artist-details")
public class ArtistDetailServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public ArtistDetailServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Artist artists = artistService.listArtists().stream().findFirst().orElse(null);

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", artists);
        templateEngine.process("artistDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long trackId = Long.valueOf(req.getParameter("trackId"));
        String artistId = req.getParameter("artistId");
        Song s = songService.findByTrackId(trackId).orElseThrow(() -> new SongNotFoundException(trackId));

        if (s != null && artistId != null) {
            Artist a = artistService.findById(Long.valueOf(artistId)).orElseThrow(() -> new ArtistNotFoundException(Long.valueOf(artistId)));
            s.getPerformers().add(a);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", s);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
