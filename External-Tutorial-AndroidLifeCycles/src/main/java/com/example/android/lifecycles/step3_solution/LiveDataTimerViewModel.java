/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycles.step3_solution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.Handler;
import android.os.SystemClock;

/**
 * A ViewModel used for the {@link ChronoActivity3}.
 */
public class LiveDataTimerViewModel extends ViewModel {

    private static final int ONE_SECOND = 1000;

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private long mInitialTime;

    public LiveDataTimerViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();
        /*


        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime.postValue(newValue);
                Log.d("ChronoActivity3", "Increase timer " + newValue);

            }
        }, ONE_SECOND, ONE_SECOND);

         */

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                mElapsedTime.postValue(newValue);
                handler.postDelayed(this, ONE_SECOND);

                if (mElapsedTime.getValue() != null) {
                    System.out.println("ViewModel LiveData Counter: " + mElapsedTime.getValue());
                }
            }
        }, ONE_SECOND);

    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }
}
