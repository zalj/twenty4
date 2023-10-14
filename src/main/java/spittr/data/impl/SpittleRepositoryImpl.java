package spittr.data.impl;

import org.springframework.stereotype.Component;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    private static List<Spittle> spittles = new ArrayList<>();

    static {
        spittles.add(new Spittle(1L, "111"));
        spittles.add(new Spittle(2L, "222"));
        spittles.add(new Spittle(3L, "333"));
        spittles.add(new Spittle(4L, "444"));
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        if (spittles.size() < count) {
            return spittles;
        }
        return spittles.subList((int)max, (int)max + count);
    }

    @Override
    public Spittle findOne(long spittleId) {
        return spittles.get((int)spittleId);
    }
}
