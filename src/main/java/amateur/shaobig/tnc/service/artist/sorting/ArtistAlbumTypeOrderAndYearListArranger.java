package amateur.shaobig.tnc.service.artist.sorting;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.service.ListArranger;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class ArtistAlbumTypeOrderAndYearListArranger implements ListArranger<Album> {

    @Override
    public List<Album> arrange(List<Album> albums) {
        return albums.stream()
                .sorted(Comparator.<Album>comparingInt(album -> album.getType().getOrder()).thenComparingInt(Album::getYear))
                .toList();
    }

}
