package amateur.shaobig.tnc.configuration;

import amateur.shaobig.tnc.calculator.mark.BasicMarkResolver;
import amateur.shaobig.tnc.calculator.mark.SongTypeMarkResolver;
import amateur.shaobig.tnc.calculator.mark.SubtractSongTypeMarkResolver;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlbumStatisticsMarkConfiguration {

    @Bean
    public SongTypeMarkResolver songTypeDefaultMarkResolver() {
        return new SongTypeMarkResolver(SongType.DEFAULT);
    }

    @Bean
    public SongTypeMarkResolver songTypeCoverMarkResolver() {
        return new SongTypeMarkResolver(SongType.COVER);
    }

    @Bean
    public SubtractSongTypeMarkResolver subtractSongTypeMarkResolver(SongTypeMarkResolver songTypeCoverMarkResolver) {
        return new SubtractSongTypeMarkResolver(songTypeCoverMarkResolver, 1);
    }

    @Bean
    public BasicMarkResolver basicMarkResolver(SongTypeMarkResolver songTypeDefaultMarkResolver, SubtractSongTypeMarkResolver subtractSongTypeMarkResolver) {
        return new BasicMarkResolver(songTypeDefaultMarkResolver, subtractSongTypeMarkResolver);
    }

}
