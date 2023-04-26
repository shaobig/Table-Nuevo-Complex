package amateur.shaobig.table_nuevo_complex.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArtistStatus {

    ACTIVE("Active"),
    DELAY("Delay"),
    RENAMED("Renamed"),
    DISPUTED("Disputed"),
    UNKNOWN("Unknown"),
    SPLIT("Split");

    private String alias;

}
