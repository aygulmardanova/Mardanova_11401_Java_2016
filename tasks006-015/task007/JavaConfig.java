package task007;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import task007.classes.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "task007.classes")
public class JavaConfig {

    @Bean
    @Qualifier("card")
    public Cheque cardcheque() {
        return new CardCheque("12345", 1);
    }

    @Bean
    @Qualifier("cash")
    public Cheque cashcheque() {
        return new CashCheque(2);
    }

    @Bean
    public RedRose redrose() {
        return new RedRose(3);
    }

    @Bean
    public WhiteAster whiteaster() {
        return new WhiteAster(5);
    }

    @Bean
    public List<Flower> flowers() {
        List<Flower> flowers = new ArrayList<>();
        flowers.add(redrose());
        flowers.add(whiteaster());
        return flowers;
    }

    @Bean
    public Bouquet mybouquet() {
        return new MyBouquet();
    }

    @Bean
    public Worker worker() {
        return Worker1.getWorker();
    }

}
