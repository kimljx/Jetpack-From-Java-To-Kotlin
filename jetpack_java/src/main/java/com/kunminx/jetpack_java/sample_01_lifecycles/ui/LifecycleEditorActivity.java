

package com.kunminx.jetpack_java.sample_01_lifecycles.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.kunminx.jetpack_java.R;
import com.kunminx.jetpack_java.common_data.APIs;
import com.kunminx.jetpack_java.common_data.Configs;
import com.kunminx.architecture.ui.BaseActivity;

/**
 * Create by KunMinX at 19/10/16
 */

public class LifecycleEditorActivity extends BaseActivity {

    private TextView mTvLocation;
    private ImageView mImageView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editor_lifecycle);

        mTvLocation = findViewById(R.id.tv_locate);
        mImageView = findViewById(R.id.iv);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        mToolbar.inflateMenu(R.menu.editor_menu);
        mToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_save) {
                showLongToast(getString(R.string.lifecycle_save_tip));
            }
            return true;
        });

        Glide.with(this).load(APIs.SCENE_URL).into(mImageView);

        mTvLocation.setOnClickListener(v -> {
            Intent intent = new Intent(this, LifecycleLocationActivity.class);
            startActivityForResult(intent, Configs.REQUEST_LOCATION_INFO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == Configs.REQUEST_LOCATION_INFO) {
            String location = data.getStringExtra(Configs.LOCATION_RESULT);
            mTvLocation.setText(location);
        }
    }
}
