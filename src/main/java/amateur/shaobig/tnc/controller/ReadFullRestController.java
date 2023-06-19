package amateur.shaobig.tnc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface ReadFullRestController<T extends Record> {

    @GetMapping(path = "/full/{id}")
    ResponseEntity<T> readFull(@PathVariable Long id);

}
