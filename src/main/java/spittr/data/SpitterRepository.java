package spittr.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spittr.Spitter;

@Component
public interface SpitterRepository {
    void save(Spitter spitter);

    Spitter findByUsername(String username);
}
