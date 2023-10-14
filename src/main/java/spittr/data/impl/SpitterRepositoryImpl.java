package spittr.data.impl;

import org.springframework.stereotype.Component;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {

    private static Map<String, Spitter> spitters = new HashMap<>();

    @Override
    public void save(Spitter spitter) {
        spitters.put(spitter.getUsername(), spitter);
    }

    @Override
    public Spitter findByUsername(String username) {
        return spitters.getOrDefault(username, null);
    }
}
