package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.CreateAlbumDto;
import amateur.shaobig.tnc.dto.album.DeleteAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.service.pool.AlbumPoolDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumPoolRestControllerTest {

    private AlbumPoolDtoService albumPoolDtoService;

    private AlbumPoolRestController albumPoolRestController;

    @BeforeEach
    void init() {
        this.albumPoolDtoService = Mockito.mock(AlbumPoolDtoService.class);

        this.albumPoolRestController = new AlbumPoolRestController(albumPoolDtoService);
    }

    @Test
    void createCheckAlbumPool() {
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        albumPoolRestController.create(sourceAlbumPool);

        AlbumPool expectedAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumPoolDtoService).create(expectedAlbumPool);
    }

    @Test
    void create() {
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        CreateAlbumPoolDto sourceResponseBody = new CreateAlbumPoolDto(new CreateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of()));
        AlbumPool sourceAlbumPool = new AlbumPool();
        Mockito.when(albumPoolDtoService.create(Mockito.any())).thenReturn(sourceResponseBody);

        ResponseEntity<CreateAlbumPoolDto> actual = albumPoolRestController.create(sourceAlbumPool);

        CreateAlbumPoolDto expectedResponseBody = new CreateAlbumPoolDto(new CreateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of()));
        ResponseEntity<CreateAlbumPoolDto> expected = ResponseEntity.status(HttpStatus.CREATED).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        LocalDateTime sourceLocalDataTime = LocalDateTime.now();
        List<ReadAllAlbumPoolDto> sourceResponseBody = List.of(new ReadAllAlbumPoolDto(1L, 1L, "", "", "ALBUM_NAME", AlbumType.LP, 0, sourceLocalDataTime));
        Mockito.when(albumPoolDtoService.readAll()).thenReturn(sourceResponseBody);

        ResponseEntity<List<ReadAllAlbumPoolDto>> actual = albumPoolRestController.readAll();

        List<ReadAllAlbumPoolDto> expectedResponseBody = List.of(new ReadAllAlbumPoolDto(1L, 1L, "", "", "ALBUM_NAME", AlbumType.LP, 0, sourceLocalDataTime));
        ResponseEntity<List<ReadAllAlbumPoolDto>> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void deleteCheckId() {
        Long sourceId = 1L;

        albumPoolRestController.delete(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumPoolDtoService).delete(expectedId);
    }

    @Test
    void delete() {
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        DeleteAlbumPoolDto sourceResponseBody = new DeleteAlbumPoolDto(new DeleteAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of()));
        Long sourceId = 1L;
        Mockito.when(albumPoolDtoService.delete(Mockito.anyLong())).thenReturn(sourceResponseBody);

        ResponseEntity<DeleteAlbumPoolDto> actual = albumPoolRestController.delete(sourceId);

        DeleteAlbumPoolDto expectedResponseBody = new DeleteAlbumPoolDto(new DeleteAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of()));
        ResponseEntity<DeleteAlbumPoolDto> expected = ResponseEntity.status(HttpStatus.OK)
                .body(expectedResponseBody);
        assertEquals(expected, actual);
    }

}
