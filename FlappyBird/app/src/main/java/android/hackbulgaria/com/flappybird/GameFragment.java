package android.hackbulgaria.com.flappybird;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment {

    View gameView;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gameView = inflater.inflate(R.layout.fragment_game, container, false);
        startMediaPlayer();
        final DrawingView myView = (DrawingView)gameView.findViewById(R.id.myView);
        GameClock clock = new GameClock();
        clock.subscribe(myView);
        return gameView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startMediaPlayer() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.prey_overture);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
}
