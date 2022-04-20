package com.onebanc.obcb;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

public class PermissionListFragment extends Fragment {
    private static final int REQUEST_CODE_CAMERA = 100;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageButton ibClose;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton ibCamera;
    private ImageButton ibMicrophone;
    private ImageButton ibPhone;
    private ImageButton ibSms;
    private ImageButton ibLocation;

    private boolean perCamera;
    private boolean perMicrophone;
    private boolean perPhone;
    private boolean perSms;
    private boolean perLocation;

    public PermissionListFragment() {
        // Required empty public constructor
    }


    public static PermissionListFragment newInstance(String param1, String param2) {
        PermissionListFragment fragment = new PermissionListFragment();
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
        return inflater.inflate(R.layout.fragment_permission_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibClose = view.findViewById(R.id.ib_cross);
        ibCamera = view.findViewById(R.id.ib_camera);
        ibLocation = view.findViewById(R.id.ib_location);
        ibPhone = view.findViewById(R.id.ib_phone);
        ibSms = view.findViewById(R.id.ib_sms);
        ibMicrophone = view.findViewById(R.id.ib_microphone);

        ibClose.setOnClickListener(view2 -> {
            Objects.requireNonNull(getActivity()).onBackPressed();
        });
        ibMicrophone.setOnClickListener(view2 -> {
            if (!perMicrophone) {


            }
        });
        ibSms.setOnClickListener(view2 -> {
            if (!perSms) {

            }
        });
        ibCamera.setOnClickListener(view2 -> {
            if (!perCamera) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA);
            }
        });
        ibPhone.setOnClickListener(view2 -> {
            if (!perPhone) {

            }
        });
        ibLocation.setOnClickListener(view2 -> {
            if (!perLocation) {

            }
        });
    }

    void applyPermissions() {
        if (perCamera) {
            ibCamera.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibCamera.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_camera));
        }
        if (perMicrophone) {
            ibMicrophone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));

        } else {
            ibMicrophone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_mic));
        }
        if (perPhone) {
            ibPhone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibPhone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_phone));
        }
        if (perSms) {
            ibSms.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));

        } else {
            ibSms.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_sms));
        }
        if (perLocation) {
            ibLocation.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibLocation.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_location));
        }
    }
}