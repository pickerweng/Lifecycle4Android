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
 *     AppLifecycleApplication.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.app;

import android.app.Application;

import com.meowmau.lifecycle4android.core.AppLifecycleManager;

/**
 * This class implements the {@link Application} to create the application lifecycle manager easily.
 * Developer needs to use this class that could retrieve the application lifecycle manager via the
 * {@link AppLifecycleApplication#getAppLifecycleManager()}.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/06
 */
public class AppLifecycleApplication extends Application {

    private AppLifecycle _appLifecycle;

    /**
     * Retrieve the application lifecycle instance.
     *
     * @return the application lifecycle instance
     */
    AppLifecycle getAppLifecycle() {
        return _appLifecycle;
    }

    /**
     * Retrieve the application lifecycle manager.
     *
     * @return the application lifecycle manager.
     */
    public AppLifecycleManager getAppLifecycleManager() {
        return _appLifecycle;
    }

    @Override
    public void onCreate() {
        _appLifecycle = new AppLifecycle(this);
        _appLifecycle.start();
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        _appLifecycle.stop();
    }
}
