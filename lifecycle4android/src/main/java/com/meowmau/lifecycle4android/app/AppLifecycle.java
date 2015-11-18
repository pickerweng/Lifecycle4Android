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
 *     AppLifecycle.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.meowmau.lifecycle4android.core.LifecycleListener;
import com.meowmau.lifecycle4android.core.AppLifecycleManager;
import com.meowmau.lifecycle4android.core.LifecycleAction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

/**
 * The implementation of the {@link AppLifecycleManager}.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/01
 */
class AppLifecycle implements AppLifecycleManager {
    /**
     * Lifecycle callback implementation that is used to receive the callback from the
     * android application.
     */
    private final LifecycleCallbackImpl _LifecycleCallback;

    /**
     * The android application instance for registering the activity lifecycle callback.
     */
    private final Application _application;

    /**
     * The list for storing all the activity/fragment lifecycle listeners.
     */
    private final ArrayList<LifecycleListener> _appLifecycleListeners = new ArrayList<>();

    /**
     * The life actions.
     */
    private final ArrayList<LifecycleAction> _actions = new ArrayList<>();

    /**
     * Create the app lifecycle manager.
     *
     * @param application the android application that to be used to receive the registration of
     *                    the activity lifecycle callback
     */
    public AppLifecycle(Application application) {
        _application = application;
        _LifecycleCallback = new LifecycleCallbackImpl(this);
    }

    @Override
    public void registerLifecycleListener(LifecycleListener listener) {
        synchronized (_appLifecycleListeners) {
            if (!_appLifecycleListeners.contains(listener)) {
                _appLifecycleListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterLifecycleListener(LifecycleListener listener) {
        synchronized (_appLifecycleListeners) {
            _appLifecycleListeners.remove(listener);
        }
    }

    @Override
    public void addAction(LifecycleAction action) {
        synchronized (_actions) {
            if (!_actions.contains(action)) {
                _actions.add(action);
            }
        }
    }

    @Override
    public void removeAction(LifecycleAction action) {
        synchronized (_actions) {
            _actions.remove(action);
        }
    }

    @Override
    public void start() {
        _application.registerActivityLifecycleCallbacks(_LifecycleCallback);
    }

    @Override
    public void stop() {
        cleanup();
    }

    @Override
    public void cleanup() {
        synchronized (_LifecycleCallback) {
            _application.unregisterActivityLifecycleCallbacks(_LifecycleCallback);
        }
        synchronized (_appLifecycleListeners) {
            _appLifecycleListeners.clear();
        }
        synchronized (_actions) {
            _actions.clear();
        }
    }

    /**
     * Retrieve all the activity lifecycle listeners.
     *
     * @return the activity lifecycle listeners
     */
    private List<LifecycleListener> getActivityLifecycleListeners() {
        List<LifecycleListener> listeners;
        synchronized (_appLifecycleListeners) {
            listeners = new ArrayList<>(_appLifecycleListeners);
        }
        return listeners;
    }

    FragmentLifecycleCallback getFragmentLifecycleCallback() {
        return _LifecycleCallback;
    }

    /**
     * Retrieve the lifecycle actions.
     *
     * @return the lifecycle actions
     */
    private List<LifecycleAction> getLifecycleActions() {
        List<LifecycleAction> actions;
        synchronized (_actions) {
            actions = new ArrayList<>(_actions);
        }
        return actions;
    }

    /**
     * This class implements the callbacks for receiving the lifecycle callback from android
     * application. This implementation will notify all the activity/fragment lifecycle listeners
     * and all the lifecycle actions.
     */
    private static final class LifecycleCallbackImpl implements Application.ActivityLifecycleCallbacks, FragmentLifecycleCallback {
        /**
         * Weak reference that references to the application lifecycle manager. It prevents the
         * retain-cycle reference.
         */
        private final WeakReference<AppLifecycle> _managerWeakRef;

        /**
         * Constructor for the lifecycle callback implementation.
         *
         * @param manager the application lifecycle manager
         */
        public LifecycleCallbackImpl(AppLifecycle manager) {
            _managerWeakRef = new WeakReference<>(manager);
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Parameter parameter = new Parameter(activity);
            parameter.set(Parameter.Key.SaveInstanceState, savedInstanceState);
            notifyLifecycle(LifecycleState.OnCreate, activity, parameter);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Parameter parameter = new Parameter(activity);
            notifyLifecycle(LifecycleState.OnStart, activity, parameter);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Parameter parameter = new Parameter(activity);
            notifyLifecycle(LifecycleState.OnResume, activity, parameter);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Parameter parameter = new Parameter(activity);
            notifyLifecycle(LifecycleState.OnPause, activity, parameter);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Parameter parameter = new Parameter(activity);
            notifyLifecycle(LifecycleState.OnStop, activity, parameter);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Parameter parameter = new Parameter(activity);
            parameter.set(Parameter.Key.SaveInstanceState, outState);
            notifyLifecycle(LifecycleState.OnSaveInstanceState, activity, parameter);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Parameter parameter = new Parameter(activity);
            notifyLifecycle(LifecycleState.OnDestroy, activity, parameter);
        }

        @TargetApi(11)
        @Override
        public void onFragmentAttach(Fragment fragment, Activity activity) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.Activity, activity);
            notifyLifecycle(LifecycleState.OnAttach, fragment, parameter);
        }

        @TargetApi(23)
        @Override
        public void onFragmentAttach(Fragment fragment, Context context) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.Context, context);
            notifyLifecycle(LifecycleState.OnAttach, fragment, parameter);
        }

        @Override
        public void onFragmentCreate(Fragment fragment, Bundle savedInstanceState) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.SaveInstanceState, savedInstanceState);
            notifyLifecycle(LifecycleState.OnCreate, fragment, parameter);
        }

        @Override
        public void onFragmentCreateView(Fragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.Inflater, inflater);
            parameter.set(Parameter.Key.Container, container);
            parameter.set(Parameter.Key.SaveInstanceState, savedInstanceState);
            notifyLifecycle(LifecycleState.OnCreateView, fragment, parameter);
        }

        @Override
        public void onFragmentActivityCreated(Fragment fragment, Bundle savedInstanceState) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.SaveInstanceState, savedInstanceState);
            notifyLifecycle(LifecycleState.OnActivityCreated, fragment, parameter);
        }

        @Override
        public void onFragmentSaveInstanceState(Fragment fragment, Bundle outState) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.SaveInstanceState, outState);
            notifyLifecycle(LifecycleState.OnSaveInstanceState, fragment, parameter);
        }

        @Override
        public void onFragmentStart(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnStart, fragment, parameter);
        }

        @Override
        public void onFragmentStop(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnStop, fragment, parameter);
        }

        @Override
        public void onFragmentResume(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnResume, fragment, parameter);
        }

        @Override
        public void onFragmentPause(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnPause, fragment, parameter);
        }

        @Override
        public void onFragmentDestroyView(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnDestroyView, fragment, parameter);
        }

        @Override
        public void onFragmentDestroy(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnDestroy, fragment, parameter);
        }

        @Override
        public void onFragmentDetach(Fragment fragment) {
            Parameter parameter = new Parameter(fragment);
            notifyLifecycle(LifecycleState.OnDetach, fragment, parameter);
        }

        @Override
        public void onFragmentActivityResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.RequestCode, valueOf(requestCode));
            parameter.set(Parameter.Key.ResultCode, valueOf(resultCode));
            parameter.set(Parameter.Key.IntentData, data);
            notifyLifecycle(LifecycleState.OnActivityResult, fragment, parameter);
        }

        @Override
        public void onFragmentConfigurationChanged(Fragment fragment, Configuration newConfig) {
            Parameter parameter = new Parameter(fragment);
            parameter.set(Parameter.Key.Configuration, newConfig);
            notifyLifecycle(LifecycleState.OnConfigurationChanged, fragment, parameter);
        }

        /**
         * Notify all the lifecycle to activity lifecycle listeners and to do the lifecycle actions.
         *
         * @param state the lifecycle state is occurred
         * @param parameter the related resources from the callback
         */
        private void notifyLifecycle(LifecycleState state, Activity activity, Parameter parameter) {
            List<LifecycleListener> listeners = _managerWeakRef.get().getActivityLifecycleListeners();
            for (LifecycleListener lifecycleListener : listeners) {
                lifecycleListener.lifecycleCallback(state, activity, parameter);
            }
            List<LifecycleAction> actions = _managerWeakRef.get().getLifecycleActions();
            for (LifecycleAction action : actions) {
                action.exec(state, activity, parameter);
            }
        }

        /**
         * Notify all the lifecycle to fragment lifecycle listeners and to do the lifecycle actions.
         *
         * @param state the lifecycle state is occurred
         * @param parameter the related resources from the callback
         */
        private void notifyLifecycle(LifecycleState state, Fragment fragment, Parameter parameter) {
            List<LifecycleListener> listeners = _managerWeakRef.get().getActivityLifecycleListeners();
            for (LifecycleListener lifecycleListener : listeners) {
                lifecycleListener.lifecycleCallback(state, fragment, parameter);
            }
            List<LifecycleAction> actions = _managerWeakRef.get().getLifecycleActions();
            for (LifecycleAction action : actions) {
                action.exec(state, fragment, parameter);
            }
        }
    }
}
