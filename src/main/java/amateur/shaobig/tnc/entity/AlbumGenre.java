package amateur.shaobig.tnc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "album_genres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlbumGenre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isMajor;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Genre genre;
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    public void setIsMajor(boolean isMajor) {
        this.isMajor = isMajor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumGenre that = (AlbumGenre) o;
        return isMajor == that.isMajor && genre.equals(that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, isMajor);
    }

}
