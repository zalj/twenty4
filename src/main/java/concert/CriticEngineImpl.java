package concert;

import java.util.Random;

public class CriticEngineImpl implements CriticismEngine {
    public CriticEngineImpl() {}

    public void setCriticismPools(String[] criticismPools) {
        this.criticismPools = criticismPools;
    }

    String[] criticismPools;

    @Override
    public String getCriticism() {
        int i = new Random().nextInt(criticismPools.length);
        return criticismPools[i];
    }
}
