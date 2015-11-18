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
 *     PrintLogAction.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.action;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import com.meowmau.lifecycle4android.core.LifecycleAction;
import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;

/**
 * This is a print log action to inject to the lifecycle manager that could notify to this action
 * when the lifecycle has been changed.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/08
 */
public class PrintLogAction implements LifecycleAction {

    private String _tag = getClass().getSimpleName();

    /**
     * Construct print log action with the tag name.
     *
     * @param tag the tag name that will be showed in the log message.
     */
    public PrintLogAction(String tag) {
        _tag = tag;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void exec(LifecycleState state, Activity activity, Parameter parameter) {
        Log.d(_tag, String.format("%s Lifecycle: %s", activity.getClass().getSimpleName(), state.toString()));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void exec(LifecycleState state, Fragment fragment, Parameter parameter) {
        Log.d(_tag, String.format("%s Lifecycle: %s", fragment.getClass().getSimpleName(), state.toString()));
    }
}
