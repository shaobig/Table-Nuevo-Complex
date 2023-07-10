package amateur.shaobig.tnc.sorting.artist;

import amateur.shaobig.tnc.entity.Album;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class AlbumTypeAndYearComparator implements Comparator<Album> {

    @Override
    public int compare(Album albumLeft, Album albumRight) {
        return Comparator.<Album>comparingInt(album -> album.getType().getOrder())
                .thenComparingInt(Album::getYear)
                .compare(albumLeft, albumRight);
    }

}
