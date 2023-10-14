package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
@Conditional(ExistHappyBean.class)
public class CDPlayer implements MediaPlayer{
    private CompactDisc compactDisc;

    @Autowired
    public void setCompactDisc(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    @Autowired
    private Environment env;

    @Override
    public void play() {
        env.getProperty("", Class.class);
        compactDisc.play();
    }
}

class ExistHappyBean implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !context.getRegistry().containsBeanDefinition("happy");
    }
}
