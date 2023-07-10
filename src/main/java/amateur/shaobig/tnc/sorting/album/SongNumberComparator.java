package amateur.shaobig.tnc.sorting.album;

import amateur.shaobig.tnc.entity.Song;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class SongNumberComparator implements Comparator<Song> {

    @Override
    public int compare(Song songLeft, Song songRight) {
        return Comparator.comparingInt(Song::getNumber).compare(songLeft, songRight);
    }

}
