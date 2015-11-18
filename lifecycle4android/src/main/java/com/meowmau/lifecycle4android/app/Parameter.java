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
 *     Parameter.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * This implementation is a data container that is used to manage the parameters that from lifecycle
 * methods which is called back to the listeners. Developer could retrieve the parameters via this
 * class. It helps to reduce the callback definition.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/07
 */
public final class Parameter {

    private final HashMap<Key, Object> _data = new HashMap<>();

    /**
     * A convenient constructor for setting the parameter with the given activity instance.
     *
     * @param activity the activity instance
     */
    public Parameter(Activity activity) {
        set(Key.Activity, activity);
    }

    /**
     * A convenient constructor for setting the parameter with the given fragment instance.
     *
     * @param fragment the fragment instance
     */
    public Parameter(Fragment fragment) {
        set(Key.Fragment, fragment);
    }

    /**
     * Set the specified key, object mapping.
     *
     * @param key the key of the data object
     * @param object the data object
     */
    public void set(Key key, Object object) {
        _data.put(key, object);
    }

    /**
     * Retrieve the object by given key.
     *
     * @param key the key of the object
     * @return the mapped object
     */
    public Object get(Key key) {
        return _data.get(key);
    }

    /**
     * It defines all the existed parameter keys for developer to retrieve the specified parameters
     * from the lifecycle methods.
     */
    public enum Key {
        /**
         * The context instance.
         */
        Context,

        /**
         * The activity instance.
         */
        Activity,

        /**
         * The fragment instance.
         */
        Fragment,

        /**
         * The saved instance state that is in {@link Bundle} data type.
         */
        SaveInstanceState,

        /**
         * The layout inflater to be used in the {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
         */
        Inflater,

        /**
         * The container that is in {@link ViewGroup} data type. It is used in the {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
         */
        Container,

        /**
         * The new configuration that is occurred when the screen rotation.
         */
        Configuration,

        /**
         * The request code for the activity result callback.
         */
        RequestCode,

        /**
         * The result code for the activity result callback.
         */
        ResultCode,

        /**
         * The intent data for the activity result callback.
         */
        IntentData,
    }
}
