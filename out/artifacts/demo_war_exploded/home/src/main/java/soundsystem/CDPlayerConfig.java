package soundsystem;

import com.springaction.knight.KnightConfig;
import concert.Audience;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackageClasses = {KnightConfig.class, CDPlayer.class, Audience.class})
@ImportResource(locations = {"CDPlayer.xml", "Knight.xml"})
public class CDPlayerConfig {
}
