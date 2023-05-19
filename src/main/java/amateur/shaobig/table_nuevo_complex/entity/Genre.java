package amateur.shaobig.table_nuevo_complex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "genres")
@NoArgsConstructor
@Getter
@Setter
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;
    @Column(nullable = false, length = 32)
    private String name;
    private boolean isMinor;

    public void setIsMinor(boolean minor) {
        isMinor = minor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return album.equals(genre.album) && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album, name);
    }

}
