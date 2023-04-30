package amateur.shaobig.table_nuevo_complex.entity;

import amateur.shaobig.table_nuevo_complex.entity.enums.SongType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "song_metadata")
@Getter
@Setter
public class SongMetadata implements Serializable {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Song song;
    private SongType type;
    private byte mark;

    public SongMetadata() {
        this.type = SongType.DEFAULT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongMetadata metadata = (SongMetadata) o;
        return id.equals(metadata.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}