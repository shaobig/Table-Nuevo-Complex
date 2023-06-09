package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class BasicAlbumMarkCalculator implements Calculator<SongMetadata> {

    private final IntegerDivideCalculator integerDivideCalculator;

    @Override
    public BigDecimal calculate(List<SongMetadata> metadataList) {
        List<Integer> defaultSongMarks = metadataList.stream()
                .filter(metadata -> SongType.DEFAULT.equals(metadata.getType()))
                .map(SongMetadata::getMark)
                .toList();
        List<Integer> coverSongMarks = metadataList.stream()
                .filter(metadata -> SongType.COVER.equals(metadata.getType()))
                .map(SongMetadata::getMark)
                .map(mark -> mark - 1)
                .toList();
        List<Integer> marks = Stream.concat(defaultSongMarks.stream(), coverSongMarks.stream()).toList();
        return getIntegerDivideCalculator().calculate(marks);
    }

}
