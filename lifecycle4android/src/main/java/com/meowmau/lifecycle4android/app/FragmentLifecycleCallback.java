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
 *     FragmentLifecycleCallback.java
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
import android.view.ViewGroup;

/**
 * The fragment lifecycle callback definition for developer to receive the callback from the
 * fragment lifecycle state.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/07
 */
interface FragmentLifecycleCallback {
    /**
     * Reference to the {@link Fragment#onAttach(Activity)}.
     */
    @TargetApi(11)
    void onFragmentAttach(Fragment fragment, Activity activity);

    /**
     * Reference to the {@link Fragment#onAttach(Context)}.
     */
    @TargetApi(23)
    void onFragmentAttach(Fragment fragment, Context context);

    /**
     * Reference to the {@link Fragment#onCreate(Bundle)}.
     */
    void onFragmentCreate(Fragment fragment, Bundle savedInstanceState);

    /**
     * Reference to the {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     */
    void onFragmentCreateView(Fragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * Reference to the {@link Fragment#onActivityCreated(Bundle)}.
     */
    void onFragmentActivityCreated(Fragment fragment, Bundle savedInstanceState);

    /**
     * Reference to the {@link Fragment#onSaveInstanceState(Bundle)}.
     */
    void onFragmentSaveInstanceState(Fragment fragment, Bundle outState);

    /**
     * Reference to the {@link Fragment#onStart()}.
     */
    void onFragmentStart(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onStop()}.
     */
    void onFragmentStop(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onResume()}.
     */
    void onFragmentResume(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onPause()}.
     */
    void onFragmentPause(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onDestroyView()}.
     */
    void onFragmentDestroyView(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onDestroy()}.
     */
    void onFragmentDestroy(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onDetach()}.
     */
    void onFragmentDetach(Fragment fragment);

    /**
     * Reference to the {@link Fragment#onActivityResult(int, int, Intent)}.
     */
    void onFragmentActivityResult(Fragment fragment, int requestCode, int resultCode, Intent data);

    /**
     * Reference to the {@link Fragment#onConfigurationChanged(Configuration)}.
     */
    void onFragmentConfigurationChanged(Fragment fragment, Configuration newConfig);
}
