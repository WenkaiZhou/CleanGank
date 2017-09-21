package com.kevin.cleangank.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kevin.cleangank.model.data.DataRepository;
import com.kevin.cleangank.model.entity.PrettyGirl;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataRepository = new DataRepository(new CompositeDisposable());
    }

    private static final String TAG = "MainActivity";

    public void click(View view) {
        dataRepository.getPrettyGirlList(10, 1).subscribe(new Consumer<List<PrettyGirl>>() {
            @Override
            public void accept(List<PrettyGirl> prettyGirls) throws Exception {
                Timber.tag("fUCKKKK").d(prettyGirls.get(0).who);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e("出错了");
                Timber.tag(TAG).e(throwable);
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
