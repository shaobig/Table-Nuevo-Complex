package amateur.shaobig.tnc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface CreateRestController<E extends Serializable, R extends Record> {

    @PostMapping(path = "/create")
    ResponseEntity<R> create(@RequestBody E entity);

}
