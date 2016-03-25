package task007.classes;

import java.util.List;

/**
 * Created by Айгуль on 17.02.2016.
 */
public interface Bouquet extends Subject {

    List<Flower> getFlowers();
    void addFlower(Flower flower); //добавить в букет цветок
    int getFlowersCount();

}
