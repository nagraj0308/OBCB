package com.onebanc.obcb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECORD_AUDIO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Objects;

public class PermissionListFragment extends Fragment {
    public static final int REQUEST_CODE_CAMERA = 100;
    public static final int REQUEST_CODE_SMS = 101;
    public static final int REQUEST_CODE_LOCATION = 102;
    private static final int REQUEST_CODE_PHONE = 102;
    private static final int REQUEST_CODE_MICROPHONE = 104;

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
            if (!checkMicrophonePermission()) {
                requestMicrophonePermission();
            }
        });
        ibSms.setOnClickListener(view2 -> {
            if (!checkSmsPermission()) {
                requestSmsReadPermission();
            }
        });
        ibCamera.setOnClickListener(view2 -> {
            if (!checkCameraPermission()) {
                requestCameraPermission();
            }
        });
        ibPhone.setOnClickListener(view2 -> {
            if (!checkPhonePermission()) {
                requestCallPhonePermission();
            }
        });
        ibLocation.setOnClickListener(view2 -> {
            if (!checkLocationPermission()) {
                requestLocationPermission();
            }
        });
        applyPermissions();
        if (checkCameraPermission() && checkLocationPermission() && checkSmsPermission() && checkPhonePermission() && checkMicrophonePermission()) {
            Navigation.findNavController(view).navigate(R.id.action_permissionListFragment_to_permissionQRScanFragment);
        }
    }

    public boolean checkCameraPermission() {
        int readPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), CAMERA);
        return readPermission == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkSmsPermission() {
        int readPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), READ_SMS);
        return readPermission == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkPhonePermission() {
        int readPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), CALL_PHONE);
        return readPermission == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkLocationPermission() {
        int readPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), ACCESS_FINE_LOCATION);
        return readPermission == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkMicrophonePermission() {
        int readPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), RECORD_AUDIO);
        return readPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{CAMERA},
                REQUEST_CODE_CAMERA);
    }

    private void requestSmsReadPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_SMS},
                REQUEST_CODE_SMS);
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_CODE_LOCATION);
    }

    private void requestMicrophonePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.RECORD_AUDIO},
                REQUEST_CODE_MICROPHONE);
    }

    private void requestCallPhonePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CALL_PHONE},
                REQUEST_CODE_PHONE);
    }


    void applyPermissions() {
        if (checkCameraPermission()) {
            ibCamera.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibCamera.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_camera));
        }
        if (checkMicrophonePermission()) {
            ibMicrophone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));

        } else {
            ibMicrophone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_mic));
        }
        if (checkPhonePermission()) {
            ibPhone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibPhone.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_phone));
        }
        if (checkSmsPermission()) {
            ibSms.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));

        } else {
            ibSms.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_sms));
        }
        if (checkLocationPermission()) {
            ibLocation.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_tick));
        } else {
            ibLocation.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.drawable_location));
        }
    }


//    public static void permissionResult(int reqCode, boolean resultCode) {
//
//        if (reqCode == REQUEST_CODE_CAMERA && !resultCode) {
//
//        }
//    }

}