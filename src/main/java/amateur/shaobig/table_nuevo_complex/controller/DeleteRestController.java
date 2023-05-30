package amateur.shaobig.table_nuevo_complex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteRestController<R extends Record> {

    @DeleteMapping(path = "/{id}")
    ResponseEntity<R> delete(@PathVariable Long id);

}
