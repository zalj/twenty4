package soundsystem.service;

import soundsystem.model.SoundSystemLog;

public interface LogDAO {
    String TABLE_NAME = "app_log";

    SoundSystemLog getSoundSystemLogById(long id);

    int insertSoundSystemLog(SoundSystemLog soundSystemLog);
}
