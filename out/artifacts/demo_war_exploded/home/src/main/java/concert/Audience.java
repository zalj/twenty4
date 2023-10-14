package concert;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
public class Audience {

    private final static Logger logger = LoggerFactory.getLogger(Audience.class);

    @Pointcut("execution(* concert.Performance.perform())")
    public void performance() {}

    @Before("performance()")
    public void silenceCellPhone() {
        logger.info("Silencing Cell Phones");
    }

    @Before("performance()")
    public void takeSeats() {
        logger.info("Taking Seats");
    }

    @AfterReturning("performance()")
    public void applause() {
        logger.info("Applause");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        logger.info("Demand A Refund");
    }
}
