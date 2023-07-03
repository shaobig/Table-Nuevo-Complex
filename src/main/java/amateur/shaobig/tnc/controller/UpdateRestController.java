package amateur.shaobig.tnc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface UpdateRestController<E extends Serializable, R extends Record> {

    @PutMapping(path = "/update")
    ResponseEntity<R> update(@RequestBody E entity);

}
