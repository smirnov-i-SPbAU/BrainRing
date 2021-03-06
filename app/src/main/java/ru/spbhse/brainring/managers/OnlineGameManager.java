package ru.spbhse.brainring.managers;

import android.util.Log;

import ru.spbhse.brainring.logic.OnlineGameAdminLogic;
import ru.spbhse.brainring.logic.OnlineGameUserLogic;
import ru.spbhse.brainring.messageProcessing.OnlineMessageProcessor;
import ru.spbhse.brainring.network.Network;
import ru.spbhse.brainring.ui.OnlineGameActivity;
import ru.spbhse.brainring.utils.Constants;

/**
 * Storage of data needed for communication between packages in online mode.
 *
 * Stores links to network, activity, admin's and player's logic and message processor
 * Has function to finish game that finishes all running processes connected with game
 */
public class OnlineGameManager implements Manager {
    private Network network;
    private OnlineGameAdminLogic adminLogic; // null if current user is not admin
    private OnlineGameUserLogic userLogic;
    private OnlineGameActivity activity;
    private OnlineMessageProcessor messageProcessor;

    public OnlineGameManager(OnlineGameActivity activity) {
        network = new Network(this);
        userLogic = new OnlineGameUserLogic(this);
        this.activity = activity;
        messageProcessor = new OnlineMessageProcessor(this);
    }

    public Network getNetwork() {
        return network;
    }

    public OnlineGameAdminLogic getAdminLogic() {
        return adminLogic;
    }

    public OnlineGameUserLogic getUserLogic() {
        return userLogic;
    }

    public OnlineGameActivity getActivity() {
        return activity;
    }

    public OnlineMessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    /**
     * Method that should be called by admin after initializing game.
     *
     * Creates admin's logic and starts game cycle
     */
    public void startOnlineGame() {
        adminLogic = new OnlineGameAdminLogic(this);
        adminLogic.newQuestion();
    }

    /** Finishes current game */
    public void finishGame() {
        if (adminLogic != null) {
            Log.d(Constants.APP_TAG,"Clearing admin logic");
            adminLogic.finishGame();
        }
        Log.d(Constants.APP_TAG,"Clearing user logic");
        userLogic.finishGame();

        Log.d(Constants.APP_TAG,"Clearing network");
        network.finish();
    }
}
