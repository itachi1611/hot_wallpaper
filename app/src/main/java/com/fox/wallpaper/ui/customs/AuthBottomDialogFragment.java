package com.fox.wallpaper.ui.customs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AuthBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener{

    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;

    @BindView(R.id.edtPassword)
    TextInputEditText edtPassword;

    @BindView(R.id.btnLogin)
    TextView btnLogin;

    public static final String TAG = "AuthBottomDialogFragment";

    private Unbinder unbinder;

    public static AuthBottomDialogFragment newInstance() {
        return new AuthBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bottom_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initView(View v) {
        unbinder = ButterKnife.bind(this, v);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin) {
            dismiss();
        }
    }

}
