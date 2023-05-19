package amateur.shaobig.table_nuevo_complex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "album_metadata")
@Getter
@Setter
public class AlbumMetadata implements Serializable {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Album album;
    private LocalDateTime time;
    private boolean isRecommendation;

    public AlbumMetadata() {
        this.time = LocalDateTime.now();
    }

    public AlbumMetadata(Album album) {
        this.time = LocalDateTime.now();
        this.album = album;
    }

    public void setIsRecommendation(boolean isRecommendation) {
        this.isRecommendation = isRecommendation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumMetadata metadata = (AlbumMetadata) o;
        return album.equals(metadata.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album);
    }

}
