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
 *     AppLifecycleFragmentTests.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.tests.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.meowmau.lifecycle4android.app.AppLifecycleApplication;
import com.meowmau.lifecycle4android.app.AppLifecycleFragment;
import com.meowmau.lifecycle4android.app.LifecycleState;
import com.meowmau.lifecycle4android.app.Parameter;
import com.meowmau.lifecycle4android.core.LifecycleListener;
import com.meowmau.lifecycle4android.core.LifecycleAction;
import com.meowmau.lifecycle4android.tests.AbstractTestRunner;
import com.meowmau.lifecycle4android.tests.mock.MockActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.robolectric.Robolectric;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link AppLifecycleFragment}.
 *
 * @author Picker Weng <pickerweng@gmail.com>
 * @since 2015/11/11
 */
public class AppLifecycleFragmentTests extends AbstractTestRunner {
    private TestAppLifecycleFragment _testSubject;
    private AppLifecycleApplication _application;
    private LifecycleListener _listener;
    private MockActivity _activity;

    @Before
    public void setUp() {
        _activity = Robolectric.buildActivity(MockActivity.class).create().start().resume().get();
        _application = (AppLifecycleApplication) _activity.getApplicationContext();

        _testSubject = new TestAppLifecycleFragment();

        FragmentManager fragmentManager = _activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(_testSubject, null);
        fragmentTransaction.commit();

        _listener = mock(LifecycleListener.class);
        _application.getAppLifecycleManager().registerLifecycleListener(_listener);
    }

    @After
    public void tearDown() {
        _application.getAppLifecycleManager().unregisterLifecycleListener(_listener);
    }

    @Test
    public void testOnAttach() throws Exception {
        _testSubject.onAttach(_activity);
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnAttach), eq(_testSubject), parameterCaptor.capture());
        assertEquals(_activity, parameterCaptor.getValue().get(Parameter.Key.Activity));
    }

    @TargetApi(23)
//    @Test
    public void testOnAttachViaContext() throws Exception {
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        _testSubject.onAttach(_application);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnAttach), eq(_testSubject), parameterCaptor.capture());
        assertEquals(_application, parameterCaptor.getValue().get(Parameter.Key.Context));
    }

    @Test
    public void testOnCreate() throws Exception {
        _testSubject.onCreate(mock(Bundle.class));
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnCreate), eq(_testSubject), parameterCaptor.capture());
        assertThat(parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState), instanceOf(Bundle.class));
    }

    @Test
    public void testOnCreateView() throws Exception {
        _testSubject.onCreateView(mock(LayoutInflater.class), mock(ViewGroup.class), mock(Bundle.class));
        ArgumentCaptor<Parameter> parameterCaptor = ArgumentCaptor.forClass(Parameter.class);
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnCreateView), eq(_testSubject), parameterCaptor.capture());
        assertThat(parameterCaptor.getValue().get(Parameter.Key.Inflater), instanceOf(LayoutInflater.class));
        assertThat(parameterCaptor.getValue().get(Parameter.Key.Container), instanceOf(ViewGroup.class));
        assertThat(parameterCaptor.getValue().get(Parameter.Key.SaveInstanceState), instanceOf(Bundle.class));
    }

    @Test
    public void testOnActivityCreated() throws Exception {
        _testSubject.onActivityCreated(mock(Bundle.class));
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnActivityCreated), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnSaveInstanceState() throws Exception {
        _testSubject.onSaveInstanceState(mock(Bundle.class));
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnSaveInstanceState), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnStart() throws Exception {
        _testSubject.onStart();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnStart), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnResume() throws Exception {
        _testSubject.onResume();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnResume), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnPause() throws Exception {
        _testSubject.onPause();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnPause), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnStop() throws Exception {
        _testSubject.onStop();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnStop), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnDestroyView() throws Exception {
        _testSubject.onDestroyView();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnDestroyView), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnDestroy() throws Exception {
        _testSubject.onDestroy();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnDestroy), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnDetach() throws Exception {
        _testSubject.onDetach();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnDetach), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnActivityResult() throws Exception {
        int requestCode = 0;
        int resultCode = Activity.RESULT_OK;
        _testSubject.onActivityResult(requestCode, resultCode, mock(Intent.class));
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnActivityResult), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testOnConfigurationChanged() throws Exception {
        _testSubject.onConfigurationChanged(mock(Configuration.class));
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnConfigurationChanged), eq(_testSubject), any(Parameter.class));
    }

    @Test
    public void testAddActionAndOnStart() throws Exception {
        LifecycleAction action = mock(LifecycleAction.class);
        _application.getAppLifecycleManager().addAction(action);

        _testSubject.onStart();
        verify(_listener).lifecycleCallback(eq(LifecycleState.OnStart), eq(_testSubject), any(Parameter.class));
        verify(action).exec(eq(LifecycleState.OnStart), eq(_testSubject), any(Parameter.class));
        _application.getAppLifecycleManager().removeAction(action);
    }

    @SuppressLint("ValidFragment")
    public class TestAppLifecycleFragment extends AppLifecycleFragment {
        public TestAppLifecycleFragment() {

        }
    }
}
