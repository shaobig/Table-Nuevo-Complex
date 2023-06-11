package amateur.shaobig.tnc;

import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.mark.BasicMarkResolver;
import amateur.shaobig.tnc.transformer.album.mark.FilteredSongTypeMarkResolver;
import amateur.shaobig.tnc.transformer.album.mark.SubtractSongTypeMarkResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public WebMvcConfigurer getWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080", "http://localhost:4200")
                        .allowedMethods("GET", "POST", "DELETE");
            }
        };
    }

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
