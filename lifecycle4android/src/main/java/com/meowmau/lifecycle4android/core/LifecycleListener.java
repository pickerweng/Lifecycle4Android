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
 *     LifecycleListener.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.core;

import android.app.Activity;
import android.app.Fragment;

import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;

/**
 * The activity/fragment lifecycle listener. Implement this interface to receive the lifecycle callback.
 *
 * Note: You will receive the callback via {@link LifecycleListener} and also via the {@link LifecycleAction}.
 * In generally, you only need to apply one approach between implementing the {@link LifecycleAction}
 * or {@link LifecycleListener}.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/01
 */
public interface LifecycleListener {
    /**
     * Called when the type of lifecycle is occurred which is from activity.
     *
     * @param state     the state of lifecycle that is occurred
     * @param activity  callback from the activity
     * @param parameter the parameter container that stores all the data from the lifecycle callback
     */
    void lifecycleCallback(LifecycleState state, Activity activity, Parameter parameter);

    /**
     * Called when the type of lifecycle is occurred which is from fragment.
     *
     * @param state     the state of lifecycle that is occurred
     * @param fragment  callback from the fragment
     * @param parameter the parameter container that stores all the data from the lifecycle callback
     */
    void lifecycleCallback(LifecycleState state, Fragment fragment, Parameter parameter);
}
