package concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Concert implements Performance{
    private static final Logger logger = LoggerFactory.getLogger(Concert.class);

    @Override
    public void perform() {
        logger.info("This is a concert");
    }
}
