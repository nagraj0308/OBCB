package com.onebanc.obcb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private boolean isLoggedIn = true;

    private Button btnClick;
    private TextView tvWm;
    private TextView tvNotUser;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnClick = view.findViewById(R.id.btn_click);
        tvWm = view.findViewById(R.id.tv_wm);
        tvNotUser = view.findViewById(R.id.tv_not_user);

        if (isLoggedIn) {
            btnClick.setText(getString(R.string.continuee));
            tvWm.setText(getString(R.string.lpfwulf));
            tvNotUser.setVisibility(View.VISIBLE);
        } else {
            btnClick.setText(getText(R.string.get_started));
            tvWm.setText(getString(R.string.ljhitj));
            tvNotUser.setVisibility(View.INVISIBLE);
        }

        btnClick.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_documentListFragment);
        });
        tvNotUser.setOnClickListener(view12 -> {
            btnClick.setText(getText(R.string.get_started));
            tvWm.setText(getString(R.string.ljhitj));
            tvNotUser.setVisibility(View.INVISIBLE);
        });
    }
}