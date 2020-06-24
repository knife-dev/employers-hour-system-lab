package handler;

import ui.listeners.OnClickListener;
import ui.views.MainView;


/*
    Handler:
        Implements Views & BO.
    Manipulates:
        Views & BO
*/

public class MainHandler extends BaseHandler {

    // Views
    MainView mainView;
    UsersControlHandler usersControlHandler;

    public MainHandler() {
        // initialize BOs
        usersControlHandler = new UsersControlHandler();
        
    }

    public void initApp() {
        mainView = new MainView(this); // Shows the main activity
        mainView.setOnClickListener(mainClickListener);
    }

    private OnClickListener mainClickListener = (event, actionCommand) -> {
        switch (actionCommand) {
            case MainView.USERS_COMMAND:
                usersControlHandler.initApp();
            break;
            case MainView.TASKS_COMMAND:
                break;
            case MainView.FEES_COMMAND:
                break;
            default:
                break;
        }
    };
    
}