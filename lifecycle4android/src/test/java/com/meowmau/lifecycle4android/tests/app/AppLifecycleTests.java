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
 *     AppLifecycleTests.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.tests.app;

import com.meowmau.lifecycle4android.app.AppLifecycleApplication;
import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;
import com.meowmau.lifecycle4android.core.AppLifecycleManager;
import com.meowmau.lifecycle4android.core.LifecycleAction;
import com.meowmau.lifecycle4android.tests.AbstractTestRunner;
import com.meowmau.lifecycle4android.tests.mock.MockActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link com.meowmau.lifecycle4android.app.AppLifecycle}.
 *
 * @author Picker Weng <pickerweng@gmail.com>
 * @since 2015/11/16
 */
public class AppLifecycleTests extends AbstractTestRunner {
    private ActivityController<MockActivity> _activityController;
    private AppLifecycleManager _testSubject;
    private AppLifecycleApplication _application;
    private MockActivity _activity;

    @Before
    public void setUp() {
        _activityController = Robolectric.buildActivity(MockActivity.class);
        _application = (AppLifecycleApplication) _activityController.get().getApplicationContext();
        _testSubject = _application.getAppLifecycleManager();
    }

    @After
    public void tearDown() {
        _testSubject.cleanup();
    }

    @Test
    public void testAddAction() {
        LifecycleAction action = mock(LifecycleAction.class);
        _testSubject.addAction(action);

        _activity = _activityController.create().get();
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(action).exec(eq(LifecycleState.OnCreate), eq(_activity), parameterCaptor.capture());
        assertNull(parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState));
    }

    @Test
    public void testAddActionWithMultipleTimes() {
        LifecycleAction action = mock(LifecycleAction.class);
        _testSubject.addAction(action);
        _testSubject.addAction(action);
        _testSubject.addAction(action);

        _activity = _activityController.create().get();
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(action).exec(eq(LifecycleState.OnCreate), eq(_activity), parameterCaptor.capture());
        assertNull(parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState));
    }

    @Test
    public void testRemoveAction() {
        LifecycleAction action = mock(LifecycleAction.class);
        _testSubject.addAction(action);
        _testSubject.removeAction(action);

        _activity = _activityController.create().get();
        verify(action, never()).exec(eq(LifecycleState.OnCreate), eq(_activity), any(Parameter.class));
    }

    @Test
    public void testNotifyActionInFragment() {
        LifecycleAction action = mock(LifecycleAction.class);
        _testSubject.addAction(action);
        _testSubject.removeAction(action);

        _activity = _activityController.create().get();
        verify(action, never()).exec(eq(LifecycleState.OnCreate), eq(_activity), any(Parameter.class));
    }
}
