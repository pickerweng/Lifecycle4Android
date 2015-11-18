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
 *     MyAppLifecycleCallbackListener.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.sample.action;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import com.meowmau.lifecycle4android.core.LifecycleListener;
import com.meowmau.lifecycle4android.core.LifecycleAction;
import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;
import com.meowmau.lifecycle4android.sample.R;

/**
 * This class is an example for developer to implement a customize listener.
 * If you have any idea to do the callback notification by yourself, you could create your customize
 * listener.
 * If you add the {@link LifecycleAction} and register your customize listener, you will receive the
 * callback from {@link LifecycleListener} and {@link LifecycleAction}.
 *
 * @author Picker Weng <pickerweng@gmail.com>
 * @since 2015/11/10
 */
public class MyAppLifecycleCallbackListener implements LifecycleListener {

    @Override
    public void lifecycleCallback(LifecycleState state, Activity activity, Parameter parameter) {
        final String appName = activity.getResources().getString(R.string.app_name);
        Log.i(appName, String.format("[%s] Activity Lifecycle %s", getClass().getSimpleName(), state.toString()));
    }

    @Override
    public void lifecycleCallback(LifecycleState state, Fragment fragment, Parameter parameter) {
        final String appName = fragment.getActivity().getResources().getString(R.string.app_name);
        Log.i(appName, String.format("[%s] Fragment Lifecycle %s", getClass().getSimpleName(), state.toString()));
    }
}
