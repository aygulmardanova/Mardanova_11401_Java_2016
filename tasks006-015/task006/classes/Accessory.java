package task006.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public interface Accessory extends Subject {

    //String getName();
    String getMainOption();
    String demonstrate();   //printing, what can do this accessory
    boolean equalsTypes(Accessory a);    //сравниваются предметы по главной опции mainOption

}
