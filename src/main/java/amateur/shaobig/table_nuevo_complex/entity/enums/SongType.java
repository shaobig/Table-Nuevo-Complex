package amateur.shaobig.table_nuevo_complex.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SongType {

    DEFAULT("Default"),
    INSTRUMENTAL("Instrumental"),
    COVER("Cover");

    private String alias;

}
