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
 *     MainApplication.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.sample;

import com.meowmau.lifecycle4android.core.LifecycleAction;
import com.meowmau.lifecycle4android.action.PrintLogAction;
import com.meowmau.lifecycle4android.app.AppLifecycleApplication;
import com.meowmau.lifecycle4android.sample.action.MyAppLifecycleCallbackListener;
import com.meowmau.lifecycle4android.sample.action.ToastAction;

/**
 * A implementation of the {@link AppLifecycleApplication} to register the {@link LifecycleAction}
 * to receive the lifecycle changed and to do the specified action.
 *
 * @author Picker Weng <pickerweng@gmail.com>
 * @since 2015/11/08
 */
public class MainApplication extends AppLifecycleApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        final String appName = getResources().getString(R.string.app_name);

        LifecycleAction printAction = new PrintLogAction(appName);
        getAppLifecycleManager().addAction(printAction);

        LifecycleAction toastAction = new ToastAction();
        getAppLifecycleManager().addAction(toastAction);

        MyAppLifecycleCallbackListener myListener = new MyAppLifecycleCallbackListener();
        getAppLifecycleManager().registerLifecycleListener(myListener);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        getAppLifecycleManager().cleanup();
    }
}
