package amateur.shaobig.tnc.configuration;

import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.mark.BasicMarkResolver;
import amateur.shaobig.tnc.transformer.album.mark.FilteredSongTypeMarkResolver;
import amateur.shaobig.tnc.transformer.album.mark.SubtractSongTypeMarkResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumStatisticsMarkConfiguration {

    @Bean
    public FilteredSongTypeMarkResolver filteredSongTypeDefaultMarkResolver() {
        return new FilteredSongTypeMarkResolver(SongType.DEFAULT);
    }

    @Bean
    public FilteredSongTypeMarkResolver filteredSongTypeCoverMarkResolver() {
        return new FilteredSongTypeMarkResolver(SongType.COVER);
    }

    @Bean
    public SubtractSongTypeMarkResolver subtractSongTypeMarkResolver(FilteredSongTypeMarkResolver filteredSongTypeCoverMarkResolver) {
        return new SubtractSongTypeMarkResolver(filteredSongTypeCoverMarkResolver, 1);
    }

    @Bean
    public BasicMarkResolver basicMarkResolver(FilteredSongTypeMarkResolver filteredSongTypeDefaultMarkResolver, SubtractSongTypeMarkResolver subtractSongTypeMarkResolver) {
        return new BasicMarkResolver(filteredSongTypeDefaultMarkResolver, subtractSongTypeMarkResolver);
    }

}
