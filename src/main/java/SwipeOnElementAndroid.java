import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidElementAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

@Action(name = "Swipe on an element up/right/left/down", description = "Swipe on an Android element in any direction")
public class SwipeOnElementAndroid implements AndroidElementAction {

    @Parameter(direction = ParameterDirection.INPUT, description = "The direction to wipe")
    public String direction;
    @Parameter(direction = ParameterDirection.INPUT, description = "How much to swipe ( Pixels )", defaultValue = "50")
    public int swipeDistance;
    @Parameter(direction = ParameterDirection.INPUT, description = "How long the swipe will be (in milliseconds)", defaultValue = "1000")
    public int swipeDuration;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper, AndroidElement element) throws FailureException {
        if(element != null){
            switch (direction.toLowerCase()) {
                case "up":
                    swipeOnElement(helper.getDriver(), element, "up");
                    break;
                case "down":
                    swipeOnElement(helper.getDriver(), element, "down");
                    break;
                case "left":
                    swipeOnElement(helper.getDriver(), element, "left");
                    break;
                case "right":
                    swipeOnElement(helper.getDriver(), element, "right");
                    break;
                default:
                    throw new FailureException(String.format("Direction '%s' is not valid!", direction));
            }
        }
        return ExecutionResult.PASSED;
    }


    private void swipeOnElement(AndroidDriver<AndroidElement> driver, AndroidElement element, String direction) {
        int x = element.getCoordinates().onPage().x;
        int y = element.getCoordinates().onPage().y;
        switch (direction) {
            case "up":
                driver.testproject().swipeGesture(swipeDuration, x, y, x, y + swipeDistance);
                break;
            case "down":
                driver.testproject().swipeGesture(swipeDuration, x, y, x, y - swipeDistance);
                break;
            case "left":
                driver.testproject().swipeGesture(swipeDuration, x, y, x - swipeDistance, y);
                break;
            case "right":
                driver.testproject().swipeGesture(swipeDuration, x , y, x + swipeDistance, y);
                break;
            default:
                break;
        }
    }
}
