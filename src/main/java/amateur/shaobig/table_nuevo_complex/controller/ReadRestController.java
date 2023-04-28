package amateur.shaobig.table_nuevo_complex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface ReadRestController<T extends Record> {

    @GetMapping(path = "/{id}")
    ResponseEntity<T> read(@PathVariable Long id);

}
