package amateur.shaobig.tnc.entity.enums;

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
