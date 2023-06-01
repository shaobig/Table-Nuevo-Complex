package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.service.ListArranger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class AlbumTypeYearListArranger implements ListArranger<Album> {

    @Override
    public List<Album> arrange(List<Album> albums) {
        return albums.stream()
                .sorted(Comparator.<Album>comparingInt(album -> album.getType().getOrder()).thenComparingInt(Album::getYear))
                .collect(Collectors.toList());
    }

}
