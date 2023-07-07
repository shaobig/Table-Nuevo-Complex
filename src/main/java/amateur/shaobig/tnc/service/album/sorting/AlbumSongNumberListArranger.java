package amateur.shaobig.tnc.service.album.sorting;

import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.service.ListArranger;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class AlbumSongNumberListArranger implements ListArranger<Song> {

    @Override
    public List<Song> arrange(List<Song> songs) {
        return songs.stream()
                .sorted(Comparator.comparingInt(Song::getNumber))
                .toList();
    }

}
