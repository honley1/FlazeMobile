package com.honley.flazemobile.bottom_nav.support;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honley.flazemobile.R;
import com.honley.flazemobile.databinding.FragmentProfileBinding;
import com.honley.flazemobile.databinding.FragmentSupportBinding;

public class SupportFragment extends Fragment {

    private FragmentSupportBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSupportBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
