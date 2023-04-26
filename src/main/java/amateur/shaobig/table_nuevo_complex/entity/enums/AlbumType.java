package amateur.shaobig.table_nuevo_complex.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumType {

    LP("LP"),
    EP("EP"),
    COMPILATION("Compilation"),
    SPLIT("Split"),
    COVER("Cover");

    private String alias;

}
