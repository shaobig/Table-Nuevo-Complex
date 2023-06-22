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
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "song_metadata")
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

    public SongMetadata() {
        this.type = SongType.DEFAULT;
    }

    public SongMetadata(SongType type, int mark) {
        this.type = type;
        this.mark = mark;
    }

    public SongMetadata(SongType type, int mark, Song song) {
        this.type = type;
        this.mark = mark;
        this.song = song;
    }

    public SongMetadata(Long id, SongType type, int mark) {
        this.id = id;
        this.type = type;
        this.mark = mark;
    }

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
