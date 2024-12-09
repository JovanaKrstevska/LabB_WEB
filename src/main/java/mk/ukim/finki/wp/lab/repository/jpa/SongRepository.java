package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);
    Optional<Song> findByTrackId(Long trackId);
    @Query(value = "INSERT INTO song_artist (song_id, artist_id) VALUES (:songId, :artistId)", nativeQuery = true)
    Artist addArtistToSong(@Param("songId") Long songId, @Param("artistId") Long artistId);
}
