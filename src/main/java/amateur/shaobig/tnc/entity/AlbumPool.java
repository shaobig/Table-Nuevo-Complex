package amateur.shaobig.tnc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "album_pool")
@NoArgsConstructor
@Getter
@Setter
public class AlbumPool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Album album;

    public AlbumPool(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumPool albumPool = (AlbumPool) o;
        return album.equals(albumPool.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album);
    }

}
