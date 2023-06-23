package amateur.shaobig.tnc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "album_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlbumMetadata implements Serializable {

    @Id
    private Long id;
    private LocalDateTime time;
    private boolean isRecommendation;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Album album;

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
