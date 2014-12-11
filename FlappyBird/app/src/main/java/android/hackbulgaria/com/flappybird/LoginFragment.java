package android.hackbulgaria.com.flappybird;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {

    LoginListener listener;
    View loginView;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        Button button = (Button)loginView.findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLogin();
            }
        });
        return loginView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (LoginListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
