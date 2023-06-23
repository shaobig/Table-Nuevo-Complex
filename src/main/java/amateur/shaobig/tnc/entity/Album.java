package amateur.shaobig.tnc.entity;

import amateur.shaobig.tnc.entity.enums.AlbumType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "albums")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "readAllAlbumWithSongs",
        attributeNodes = {
                @NamedAttributeNode(value = "metadata"),
                @NamedAttributeNode(value = "artist", subgraph = "readArtistLocation"),
                @NamedAttributeNode(value = "songs", subgraph = "readSongMetadata")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "readArtistLocation",
                        attributeNodes = {@NamedAttributeNode(value = "location")}
                ),
                @NamedSubgraph(
                        name = "readSongMetadata",
                        attributeNodes = {@NamedAttributeNode(value = "metadata")}
                )
        }
)
@DynamicUpdate
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private AlbumType type;
    @OneToOne(mappedBy = "album", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private AlbumMetadata metadata;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Artist artist;
    @OneToMany(mappedBy = "album", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Genre> genres;
    @OneToMany(mappedBy = "album", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Song> songs;

    public void setMetadata(AlbumMetadata metadata) {
        metadata.setAlbum(this);
        this.metadata = metadata;
    }

    public void setGenres(List<Genre> genres) {
        Optional.ofNullable(genres)
                .ifPresent(presentedGenres -> {
                    presentedGenres.forEach(genre -> genre.setAlbum(this));
                    this.genres = presentedGenres;
                });
    }

    public void setSongs(List<Song> songs) {
        Optional.ofNullable(songs)
                .ifPresent(presentedSongs -> {
                    presentedSongs.forEach(song -> song.setAlbum(this));
                    this.songs = presentedSongs;
                });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return name.equals(album.name) && year.equals(album.year) && type == album.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, type);
    }

}
