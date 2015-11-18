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
 *     ParameterTests.java
 *
 * Author:
 *     Picker Weng (pickerweng@gmail.com)
 */

package com.meowmau.lifecycle4android.tests;

import android.app.Activity;
import android.app.Fragment;

import com.meowmau.lifecycle4android.app.Parameter;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test for {@link Parameter}.
 *
 * @author Picker Weng
 * @since 2015/11/10
 */
public class ParameterTests extends AbstractTestRunner {
    @Test
    public void testCreateWithActivity() throws Exception {
        Activity activity = Mockito.mock(Activity.class);
        Parameter testSubject = new Parameter(activity);
        Assert.assertEquals(activity, testSubject.get(Parameter.Key.Activity));
    }

    @Test
    public void testCreateWithFragment() throws Exception {
        Fragment fragment = Mockito.mock(Fragment.class);
        Parameter testSubject = new Parameter(fragment);
        Assert.assertEquals(fragment, testSubject.get(Parameter.Key.Fragment));
    }

    @Test
    public void testSetData() throws Exception {
        Fragment fragment = Mockito.mock(Fragment.class);
        Parameter testSubject = new Parameter(fragment);
        testSubject.set(Parameter.Key.RequestCode, 2);
        Assert.assertEquals(2, testSubject.get(Parameter.Key.RequestCode));
    }
}