package amateur.shaobig.table_nuevo_complex.entity;

import amateur.shaobig.table_nuevo_complex.entity.enums.ArtistStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "artists")
@NoArgsConstructor
@Getter
@Setter
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Location location;
    private ArtistStatus status;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Album> albums;

    public void setAlbums(List<Album> albums) {
        Optional.ofNullable(albums)
                .ifPresent(presentedAlbums -> {
                    presentedAlbums.forEach(album -> album.setArtist(this));
                    this.albums = presentedAlbums;
                });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id.equals(artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
