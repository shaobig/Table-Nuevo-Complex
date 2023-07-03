package amateur.shaobig.tnc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface FindRestController<E extends Serializable> {

    @PostMapping(path = "/find")
    ResponseEntity<Void> find(@RequestBody E entity);

}
