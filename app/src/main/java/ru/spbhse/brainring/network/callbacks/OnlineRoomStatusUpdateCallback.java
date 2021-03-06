package ru.spbhse.brainring.network.callbacks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateCallback;

import java.util.List;

import ru.spbhse.brainring.network.Network;
import ru.spbhse.brainring.utils.Constants;

/** Reacts on room status update. Used in {@code Network} */
public class OnlineRoomStatusUpdateCallback extends RoomStatusUpdateCallback {
    private Network network;

    public OnlineRoomStatusUpdateCallback(Network network) {
        this.network = network;
    }

    @Override
    public void onRoomConnecting(@Nullable Room room) {
        Log.d(Constants.APP_TAG, "onRoomConnecting");
        network.setRoom(room);
    }

    @Override
    public void onRoomAutoMatching(@Nullable Room room) {
        Log.d(Constants.APP_TAG, "onRoomAutoMatching");
        network.setRoom(room);
    }

    @Override
    public void onPeerInvitedToRoom(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeerInvitedToRoom");
        network.setRoom(room);
    }

    @Override
    public void onPeerDeclined(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeerDeclined");
        network.setRoom(room);
    }

    @Override
    public void onPeerJoined(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeerJoined");
        network.setRoom(room);
    }

    @Override
    public void onPeerLeft(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeerLeft");
        network.setRoom(room);
        network.waitOrFinish();
    }

    @Override
    public void onConnectedToRoom(@Nullable Room room) {
        Log.d(Constants.APP_TAG, "onConnectedToRoom");
        network.setRoom(room);
    }

    @Override
    public void onDisconnectedFromRoom(@Nullable Room room) {
        Log.d(Constants.APP_TAG, "onDisconnectedFromRoom");
        network.setRoom(room);
        network.waitOrFinish();
    }

    @Override
    public void onPeersConnected(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeersConnected");
        network.setRoom(room);
    }

    @Override
    public void onPeersDisconnected(@Nullable Room room, @NonNull List<String> list) {
        Log.d(Constants.APP_TAG, "onPeersDisconnected");
        network.setRoom(room);
        network.waitOrFinish();
    }

    /**
     * Checks whether {@code onRoomConnected} had already been called.
     * If so and I am server, starts game
     */
    @Override
    public void onP2PConnected(@NonNull String s) {
        Log.d(Constants.APP_TAG, "onP2PConnected " + s);
        network.plusConnection();
        if (network.getConnections() == 2 && network.iAmServer()) {
            network.getManager().startOnlineGame();
        }
    }


    @Override
    public void onP2PDisconnected(@NonNull String s) {
        Log.d(Constants.APP_TAG, "onP2PDisconnected " + s);
        network.waitOrFinish();
    }
}
