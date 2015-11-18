/*
 * Copyright (c) 2015, Picker Weng
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of Lifecycle4Android nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Project:
 *     Lifecycle4Android
 *
 * File:
 *     AppLifecycleFragment.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Since android doesn't support fragment lifecycle callback now, we receive the callback manually.
 * This abstract fragment is an implementation that supports fragment lifecycle callback.
 *
 * Developers who want to receive the fragment lifecycle callback, please inherit this class.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/07
 */
public abstract class AppLifecycleFragment extends Fragment {

    /**
     * Retrieve the application lifecycle instance.
     *
     * @return the application lifecycle instance
     */
    private AppLifecycle getAppLifecycle() {
        AppLifecycleApplication application = (AppLifecycleApplication) getActivity().getApplicationContext();
        return application.getAppLifecycle();
    }

    @TargetApi(11)
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentAttach(this, activity);
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentAttach(this, context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentCreate(this, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentCreateView(this, inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentActivityCreated(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentSaveInstanceState(this, outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentStop(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentDestroy(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentDetach(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getAppLifecycle().getFragmentLifecycleCallback().onFragmentConfigurationChanged(this, newConfig);
    }
}
