package spittr.data.impl;

import org.springframework.stereotype.Component;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    private static List<Spittle> spittles = new ArrayList<>();

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return spittles.subList((int)max, (int)max + count);
    }

    @Override
    public Spittle findOne(long spittleId) {
        return spittles.get((int)spittleId);
    }
}
