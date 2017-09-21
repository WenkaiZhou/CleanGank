package com.kevin.cleangank.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kevin.cleangank.model.data.DataRepository;
import com.kevin.cleangank.model.entity.RestVideo;

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
//        dataRepository.getPrettyGirlList(10, 1).subscribe(
//                new Consumer<List<PrettyGirl>>() {
//                    @Override
//                    public void accept(List<PrettyGirl> prettyGirls) {
//                        Timber.tag(TAG).d(prettyGirls.get(0).who);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        Timber.tag(TAG).e("出错了");
//                    }
//                });

        dataRepository.getRestVideoList(10, 1).subscribe(
                new Consumer<List<RestVideo>>() {
                    @Override
                    public void accept(List<RestVideo> restVideos) {
                        Timber.tag(TAG).d(restVideos.get(0).url);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                    }
                }
        );

    }
}
