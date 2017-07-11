package com.brouding.blockbuttonsample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.brouding.blockbutton.BlockButton;
import com.brouding.blockbuttonsample.Pref.Pref;
import com.brouding.simpledialog.SimpleDialog;

public class SampleActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity thisActivity;
    private SharedPreferences mPreference;

    private BlockButton btnResetGuidePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        thisActivity = this;
        mPreference  = getSharedPreferences(Pref.PREFERENCE_NAME, MODE_PRIVATE);

        BlockButton btnShowBasicDialog = (BlockButton) findViewById(R.id.btn_show_basic_dialog);
        btnShowBasicDialog.setOnClickListener(this);

        BlockButton btnShowProgressDialog = (BlockButton) findViewById(R.id.btn_show_progress_dialog);
        btnShowProgressDialog.setOnClickListener(this);

        BlockButton btnShowGuideDialog = (BlockButton) findViewById(R.id.btn_show_guide_dialog);
        btnShowGuideDialog.setOnClickListener(this);

        btnResetGuidePref = (BlockButton) findViewById(R.id.btn_reset_guide);
        btnResetGuidePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGuidePref();

                btnResetGuidePref.setEnabled(false);
            }
        });

        btnResetGuidePref.setEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        resetGuidePref();
    }

    private void resetGuidePref() {
        SharedPreferences.Editor edit = mPreference.edit();
        edit.putBoolean(Pref.KEY_FIRST_WELCOME, Pref.FIRST_WELCOME_DEFAULT);
        edit.apply();
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.btn_show_basic_dialog:
                new SimpleDialog.Builder(thisActivity)
                        .setContent("This is basic SimpleDialog :)")
                        .setBtnConfirmText("Check!", true)

                        // Customizing

                        //.setTitle("Hello !", true)
                        //.setCancelable(true)          // Default value is false
                        //.onConfirm(new SimpleDialog.BtnCallback() {
                        //    @Override
                        //    public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which) {
                        //        // Do something
                        //    }
                        //})
                        //.setBtnCancelText("Cancel", false)
                        //.onCancel(new SimpleDialog.BtnCallback() {
                        //    @Override
                        //    public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which) {
                        //        // Do something
                        //    }
                        //})
                        .show();
                break;

            case R.id.btn_show_progress_dialog:
                new SimpleDialog.Builder(thisActivity)
                        .setContent("This is progress SimpleDialog :)")
                        .setProgressGIF(R.raw.loading_default)
                        .setBtnCancelText("Cancel", false)

                        // Customizing

                        //.setBtnCancelTextColor(R.color.colorPrimary)
                        //.setBtnCancelShowTime(2000)
                        //.onCancel(new SimpleDialog.BtnCallback() {
                        //    @Override
                        //    public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which) {
                        //        // thisActivity.finish();
                        //    }
                        //})
                        //.showProgress(true)
                        .show();
                break;

            case R.id.btn_show_guide_dialog:
                new SimpleDialog.Builder(thisActivity)
                        .setTitle("Hello !", true)      // Not necessary
                        .setContent("This is guide SimpleDialog :)\n\n- You can pinch the view !")
                        .setGuideImage(R.drawable.image_guide_pinch)    // Not necessary
                        .setPreferenceName(Pref.PREFERENCE_NAME)
                        .setPermanentCheckKey(Pref.KEY_FIRST_WELCOME)
                        .onConfirmWithPermanentCheck(new SimpleDialog.BtnCallback() {
                            @Override
                            public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which) {
                                btnResetGuidePref.setEnabled(true);
                            }
                        })
                        .setBtnConfirmText("Check!", false)

                        // Customizing

                        //.setTitle("Hello !", true)
                        .showIfPermanentValueIsFalse();
                break;


        }
    }
}
