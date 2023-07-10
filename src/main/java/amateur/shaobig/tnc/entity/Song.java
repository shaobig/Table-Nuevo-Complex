package amateur.shaobig.tnc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private String name;
    @OneToOne(mappedBy = "song", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private SongMetadata metadata;
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    public void setMetadata(SongMetadata metadata) {
        metadata.setSong(this);
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return number.equals(song.number) && name.equals(song.name) && album.equals(song.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, album);
    }

}
