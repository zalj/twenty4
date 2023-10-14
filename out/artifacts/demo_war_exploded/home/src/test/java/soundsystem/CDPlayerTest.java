package soundsystem;

import com.springaction.knight.BraveKnight;
import com.springaction.knight.Knight;
import com.springaction.knight.KnightConfig;
import concert.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CDPlayerConfig.class})
public class CDPlayerTest {
    @Autowired
    private Performance performance;

    @Test
    public void play() {
        performance.perform();
    }
}
