package Task002;

/**
 * Created by Айгуль on 22.02.2016.
 */
public class InverseMatrixDoesNotExistException extends Exception{
    @Override
    public String toString () {
        return "Determinant is 0. Inverse matrix doesn't exists.";
    }
}
