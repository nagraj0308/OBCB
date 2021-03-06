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
import android.widget.ImageButton;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterNameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnNext;
    private ImageButton ivClose;

    public EnterNameFragment() {
        // Required empty public constructor
    }


    public static EnterNameFragment newInstance(String param1, String param2) {
        EnterNameFragment fragment = new EnterNameFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.btn_ready);
        btnNext.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_enterNameFragment_to_enterPhoneFragment);
        });

        ivClose = view.findViewById(R.id.ib_cross);

        ivClose.setOnClickListener(view2 -> {
            Objects.requireNonNull(getActivity()).onBackPressed();
        });
    }
}