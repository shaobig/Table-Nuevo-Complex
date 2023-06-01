package amateur.shaobig.tnc.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumType {

    LP("LP", 1),
    EP("EP", 2),
    COMPILATION("Compilation", 3),
    SPLIT("Split", 4),
    COVER("Cover", 5);

    private String alias;
    private int order;

}
