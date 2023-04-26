package amateur.shaobig.table_nuevo_complex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ReadAllRestController<R extends Record> {

    @GetMapping(path = "/all")
    ResponseEntity<List<R>> readAll();

}
