package com.kevin.cleangank.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kevin.cleangank.model.entity.PrettyGirl;
import com.kevin.cleangank.model.net.HttpBuilder;
import com.kevin.cleangank.model.net.HttpHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SETTING = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final String TAG = "MainActivity";

    public void click(View view) {
        HttpHelper.request(new CompositeDisposable(),
                HttpBuilder.getRestService().getPrettyGirl(10, 1),
                new Consumer<PrettyGirl>() {
                    @Override
                    public void accept(PrettyGirl prettyGirl) {
                        Timber.tag("aa").d(prettyGirl.list.get(0).toString());
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Timber.tag("aa").d(throwable.toString());
                        Log.d(TAG, "accept() called with: throwable = [" + throwable + "]");
                    }
                });

//        new RxPermissions(MainActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean granted) throws Exception {
//                                            if (granted) {
//                        // All requested permissions are granted
//                    } else {
//                        // At least one permission is denied
//                    }
//                    }
//                });
//        rxPermissions
//                .request(Manifest.permission.CAMERA,
//                        Manifest.permission.READ_PHONE_STATE)
//                .subscribe(granted -> {
//                    if (granted) {
//                        // All requested permissions are granted
//                    } else {
//                        // At least one permission is denied
//                    }
//                });
    }
}
