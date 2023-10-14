package spittr.data;

import org.springframework.stereotype.Component;
import spittr.Spitter;

@Component
public interface SpitterRepository {
    void save(Spitter spitter);

    Spitter findByUsername(String username);
}
