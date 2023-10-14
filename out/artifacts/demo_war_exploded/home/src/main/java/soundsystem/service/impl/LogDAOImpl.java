package soundsystem.service.impl;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import soundsystem.model.SoundSystemLog;
import soundsystem.service.LogDAO;

import java.util.HashMap;
import java.util.Map;

@org.aspectj.lang.annotation.Aspect
public class LogDAOImpl implements LogDAO {
    Map<Long, SoundSystemLog> database = new HashMap<>();

    private final SoundSystemLog defaultSoundSystemLog = new SoundSystemLog();

    @Override
    public SoundSystemLog getSoundSystemLogById(long id) {
        return database.getOrDefault(id, defaultSoundSystemLog);
    }

    @Override
    public int insertSoundSystemLog(SoundSystemLog soundSystemLog) {
        if (!database.containsKey(soundSystemLog.getId())) {
            database.put(soundSystemLog.getId(), soundSystemLog);
            return 1;
        }
        return 0;
    }
}
