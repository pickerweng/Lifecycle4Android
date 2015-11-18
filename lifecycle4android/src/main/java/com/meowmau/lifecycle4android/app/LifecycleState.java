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
 *     LifecycleState.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * The activity/fragment lifecycle state enumeration. These definitions are used to help lifecycle
 * action to sure about the which lifecycle state is occurred.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/01
 */
public enum LifecycleState {
    /**
     * Reference to the {@link Activity#onCreate(Bundle)} or {@link Fragment#onCreate(Bundle)}.
     */
    OnCreate ("OnCreate"),

    /**
     * Reference to the {@link Activity#onSaveInstanceState(Bundle)} or {@link Fragment#onSaveInstanceState(Bundle)}.
     */
    OnSaveInstanceState ("OnSaveInstanceState"),

    /**
     * Reference to the {@link Activity#onStart()} or {@link Fragment#onStart()}.
     */
    OnStart ("OnStart"),

    /**
     * Reference to the {@link Activity#onResume()} or {@link Fragment#onResume()}.
     */
    OnResume ("OnResume"),

    /**
     * Reference to the {@link Activity#onPause()} or {@link Fragment#onPause()}.
     */
    OnPause ("OnPause"),

    /**
     * Reference to the {@link Activity#onStop()} or {@link Fragment#onStop()}.
     */
    OnStop ("OnStop"),

    /**
     * Reference to the {@link Activity#onDestroy()} or {@link Fragment#onDestroy()}.
     */
    OnDestroy ("OnDestroy"),

    /**
     * Reference to the {@link Fragment#onAttach(Activity)} and {@link Fragment#onAttach(Context)}.
     */
    OnAttach ("OnAttach"),

    /**
     * Reference to the {@link Fragment#onDetach()}.
     */
    OnDetach ("OnDetach"),

    /**
     * Reference to the {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     */
    OnCreateView ("OnCreateView"),

    /**
     * Reference to the {@link Fragment#onActivityCreated(Bundle)}.
     */
    OnActivityCreated ("OnActivityCreated"),

    /**
     * Reference to the {@link Fragment#onDestroyView()}.
     */
    OnDestroyView ("OnDestroyView"),

    /**
     * Reference to the {@link Activity#onConfigurationChanged(Configuration)}.
     */
    OnConfigurationChanged("OnConfigurationChanged"),

    /**
     * Reference to the {@link Activity#onActivityResult(int, int, Intent)}.
     */
    OnActivityResult("OnActivityResult");

    /**
     * Store the name of state.
     */
    private final String _name;

    LifecycleState(String s) {
        _name = s;
    }

    /**
     * Compare with the given string.
     *
     * @param otherName the compared string
     * @return true if are same, false otherwise
     */
    public boolean equals(String otherName) {
        return (otherName != null) && _name.equals(otherName);
    }

    /**
     * Convert to string.
     *
     * @return the string of the enumeration
     */
    public String toString() {
        return _name;
    }
}
