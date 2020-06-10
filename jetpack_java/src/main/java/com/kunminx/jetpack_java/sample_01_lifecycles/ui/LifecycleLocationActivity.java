

package com.kunminx.jetpack_java.sample_01_lifecycles.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.BaseActivity;
import com.kunminx.jetpack_java.R;
import com.kunminx.jetpack_java.common_data.Configs;
import com.kunminx.jetpack_java.common_ui.adapter.LocationAdapter;
import com.kunminx.jetpack_java.sample_01_lifecycles.domain.LifecycleLocationManager;

/**
 * Create by KunMinX at 19/10/16
 */

public class LifecycleLocationActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private LocationAdapter mLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location_lifecycles);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());

        mRecyclerView = findViewById(R.id.rv);

        mRecyclerView.setAdapter(mLocationAdapter = new LocationAdapter(getApplicationContext(), locationBean -> {
            Intent intent = new Intent();
            intent.putExtra(Configs.LOCATION_RESULT, locationBean.getLocationName());
            setResult(RESULT_OK, intent);
            finish();
        }));

        getLifecycle().addObserver(LifecycleLocationManager.getInstance());

        LifecycleLocationManager.getInstance().setILocationCallback(list -> {
            runOnUiThread(() -> {
                mLocationAdapter.submitList(list);
            });
        });
    }

}
