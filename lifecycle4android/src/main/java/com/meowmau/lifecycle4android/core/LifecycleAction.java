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
 *     LifecycleAction.java
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
 * The lifecycle action helps developers to customize the different actions to execute when the
 * lifecycle state is occurred. You could customize the the action via implementing this interface.
 * These actions can be arranged into the application lifecycle manager, and then be called when
 * entering the lifecycle state.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/01
 */
public interface LifecycleAction {
    /**
     * Execute the action that lifecycle is from activity.
     *
     * @param state the lifecycle state is occurred
     * @param activity the callback from the activity
     * @param parameter the parameters that contained all the related resources
     */
    void exec(LifecycleState state, Activity activity, Parameter parameter);

    /**
     * Execute the action that lifecycle is from fragment.
     *
     * @param state the lifecycle state is occurred
     * @param fragment the callback from the fragment
     * @param parameter the parameters that contained all the related resources
     */
    void exec(LifecycleState state, Fragment fragment, Parameter parameter);
}
