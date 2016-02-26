package Task004.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public interface Flower extends Selling, Subject{

    //String getName();
    String getDescription();
    String getColor();
    String getType();
    boolean equalsType(Flower f);
    boolean equals(Flower f);
}
