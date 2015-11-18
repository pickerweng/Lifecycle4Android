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
 *     ActivityAppLifecycleTests.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.tests.app;

import android.os.Bundle;

import com.meowmau.lifecycle4android.app.AppLifecycleApplication;
import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;
import com.meowmau.lifecycle4android.core.LifecycleListener;
import com.meowmau.lifecycle4android.core.AppLifecycleManager;
import com.meowmau.lifecycle4android.tests.AbstractTestRunner;
import com.meowmau.lifecycle4android.tests.mock.MockActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Testing for the Activity lifecycle.
 *
 * @author Picker Weng <pickerweng@gmail.com>
 * @since 2015/11/17
 */
public class ActivityAppLifecycleTests extends AbstractTestRunner {
    private ActivityController<MockActivity> _activityController;
    private AppLifecycleManager _testSubject;
    private AppLifecycleApplication _application;
    private MockActivity _activity;
    private LifecycleListener _listener;

    @Before
    public void setUp() {
        _activityController = Robolectric.buildActivity(MockActivity.class);
        _application = (AppLifecycleApplication) _activityController.get().getApplicationContext();
        _testSubject = _application.getAppLifecycleManager();

        _listener = mock(LifecycleListener.class);
        _testSubject.registerLifecycleListener(_listener);
    }

    @After
    public void tearDown() {
        _testSubject.cleanup();
    }

    @Test
    public void testActivityCreated() {
        Bundle savedInstanceState = new Bundle();
        _activity = _activityController.create(savedInstanceState).get();
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnCreate), eq(_activity), parameterCaptor.capture());
        assertEquals(savedInstanceState, parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState));
    }

    @Test
    public void testActivityStarted() {
        _activity = _activityController.create().start().get();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnStart), eq(_activity), any(Parameter.class));
    }

    @Test
    public void testActivityResumed() {
        _activity = _activityController.create().start().resume().get();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnResume), eq(_activity), any(Parameter.class));
    }

    @Test
    public void testActivityPaused() {
        _activity = _activityController.create().start().resume().pause().get();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnPause), eq(_activity), any(Parameter.class));
    }

    @Test
    public void testActivityStopped() {
        _activity = _activityController.create().start().resume().pause().stop().get();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnStop), eq(_activity), any(Parameter.class));
    }

    @Test
    public void testActivitySaveInstanceState() {
        Bundle savedInstanceState = new Bundle();
        _activity = _activityController.create().saveInstanceState(savedInstanceState).get();
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnSaveInstanceState), eq(_activity), parameterCaptor.capture());
        assertEquals(savedInstanceState, parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState));
    }

    @Test
    public void testActivityDestroyed() {
        _activity = _activityController.create().start().resume().pause().stop().destroy().get();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnDestroy), eq(_activity), any(Parameter.class));
    }
}
