public interface Client {
    boolean order(String courseName);
    String askAdvice(String ingredient);
    void likeOrder(String courseName, boolean like);
    int askCost(String courseName);
    void complain(String courseName);
}
