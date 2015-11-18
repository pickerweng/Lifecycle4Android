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
 *     AppLifecycleManager.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.core;

/**
 * The application lifecycle manager to manage and to process the activity/fragment lifecycle.
 * developer could implement the {@link LifecycleListener} and register the listener into this
 * manager. The manager will notify the state of activity lifecycle to these listeners. Therefore,
 * you could execute the customize operations in the listener.
 *
 * Otherwise, developer could also implement the {@link LifecycleAction} and add it into the manager.
 * The manager will callback to the action when the state of activity lifecycle is occurred.
 * This could help you to separate action logic and to develop the customize action against the
 * specified state of activity lifecycle.
 *
 * In generally, you only need to apply one approach between {@link LifecycleListener} and
 * {@link LifecycleAction}.
 *
 * @author Picker Weng (pickerweng@gmail.com)
 * @since 2015/11/01
 */
public interface AppLifecycleManager {
    /**
     * Register the activity/fragment lifecycle listener.
     * The listener is used to listen the occurring lifecycle state of activity or fragment.
     *
     * @param listener the activity/fragment lifecycle listener
     */
    void registerLifecycleListener(LifecycleListener listener);

    /**
     * Unregister the activity/fragment lifecycle listener.
     *
     * @param listener the activity/fragment lifecycle listener
     */
    void unregisterLifecycleListener(LifecycleListener listener);

    /**
     * Add the customized action to do when the lifecycle state is occurred.
     *
     * @param action the customized action will be added
     */
    void addAction(LifecycleAction action);

    /**
     * Remove the action.
     *
     * @param action the action will be removed
     */
    void removeAction(LifecycleAction action);

    /**
     * Start the lifecycle observation.
     */
    void start();

    /**
     * Stop the lifecycle observation.
     */
    void stop();

    /**
     * Clean up the resource of application lifecycle manager. It will remove all the listeners and
     * the actions in this manager.
     */
    void cleanup();
}
