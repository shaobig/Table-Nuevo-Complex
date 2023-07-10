package amateur.shaobig.tnc.configuration;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.sorting.ComparatorListArranger;
import amateur.shaobig.tnc.sorting.album.SongNumberComparator;
import amateur.shaobig.tnc.sorting.artist.AlbumTypeAndYearComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComparatorListArrangerConfiguration {

    @Bean
    public ComparatorListArranger<Song> songComparatorListArranger(SongNumberComparator songNumberComparator) {
        return new ComparatorListArranger<>(songNumberComparator);
    }

    @Bean
    public ComparatorListArranger<Album> albumComparatorListArranger(AlbumTypeAndYearComparator albumTypeAndYearComparator) {
        return new ComparatorListArranger<>(albumTypeAndYearComparator);
    }

}
