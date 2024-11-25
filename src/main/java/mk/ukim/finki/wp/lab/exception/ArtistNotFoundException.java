package mk.ukim.finki.wp.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(Long id){
        super(String.format("Ne postoi Artist so toj id"));
    }
}