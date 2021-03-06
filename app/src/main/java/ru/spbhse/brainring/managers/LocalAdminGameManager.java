package ru.spbhse.brainring.managers;

import ru.spbhse.brainring.logic.LocalGameAdminLogic;
import ru.spbhse.brainring.messageProcessing.LocalAdminMessageProcessor;
import ru.spbhse.brainring.network.LocalNetworkAdmin;
import ru.spbhse.brainring.ui.JuryActivity;

/**
 * Storage of data needed for communication between packages in local mode by admin.
 *
 * Stores links to network, activity, admin's logic and message processor
 * Has function to finish game that finishes all running processes connected with game
 */
public class LocalAdminGameManager implements Manager {
    private LocalNetworkAdmin network;
    private JuryActivity activity;
    private LocalGameAdminLogic logic;
    private LocalAdminMessageProcessor processor;
    private boolean finished = false;

    public LocalAdminGameManager(JuryActivity juryActivity, int firstTimer, int secondTimer) {
        activity = juryActivity;
        processor = new LocalAdminMessageProcessor(this);
        logic = new LocalGameAdminLogic(firstTimer, secondTimer, this);
        network = new LocalNetworkAdmin(this);
    }

    public LocalNetworkAdmin getNetwork() {
        return network;
    }

    public JuryActivity getActivity() {
        return activity;
    }

    public LocalGameAdminLogic getLogic() {
        return logic;
    }

    public LocalAdminMessageProcessor getProcessor() {
        return processor;
    }

    public void finishGame() {
        if (!finished) {
            finished = true;
            logic.finishGame();
            network.finish();
            activity.finish(); // TODO
        }
    }
}
