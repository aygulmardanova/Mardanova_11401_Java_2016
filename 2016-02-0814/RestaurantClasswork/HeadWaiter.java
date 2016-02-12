/**
 * Created by Айгуль on 12.02.2016.
 */
public class HeadWaiter implements Waiter, ComplainAcceptable {
    @Override
    public void listenTheComplaint(Cookable courseName) {
        System.out.println("I'm listening to you");
        answerToComplaint(courseName);
    }

    @Override
    public void sendToCookAgain(Cookable courseName) {
        courseName.cookAgain();
    }

    @Override
    public void answerToComplaint(Cookable courseName) {
        sendToCookAgain(courseName);
    }

    @Override
    public void takeOrder(String courseName) {

    }

    @Override
    public void offerCourse(String ingredient) {

    }
}
