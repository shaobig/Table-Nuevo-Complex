package amateur.shaobig.tnc.entity;

import amateur.shaobig.tnc.entity.enums.ArtistStatus;
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
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Getter
@Setter
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private ArtistStatus status;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Location location;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST)
    private List<Album> albums;

    public Artist(Long id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = ArtistStatus.ACTIVE;
    }

    public Artist(String name, Location location) {
        this.name = name;
        this.location = location;
        this.status = ArtistStatus.ACTIVE;
    }

    public Artist(String name, Location location, ArtistStatus status) {
        this.name = name;
        this.location = location;
        this.status = status;
    }

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
        return name.equals(artist.name) && location.equals(artist.location) && status == artist.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, status);
    }

}
