package amateur.shaobig.tnc.entity;

import amateur.shaobig.tnc.entity.enums.SongType;
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
import java.util.Objects;

@Entity
@Table(name = "song_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongMetadata implements Serializable {

    @Id
    private Long id;
    private SongType type;
    private int mark;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Song song;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongMetadata that = (SongMetadata) o;
        return song.equals(that.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song);
    }

}
