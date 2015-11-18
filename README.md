# Lifecycle4Android

Lifecycle4Android provides the interface for developers to wrap callback into Activity/Fragment lifecycle easily. This library is introduced in Android 4 (API Level 14).

### Problem

Android native support that [ActivityLifecycleCallbacks](http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html) for developer to implement their callback wrapper to receive the changed of lifecycle state. But... the callback methods are too much such as [onActivityCreated](http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html#onActivityCreated(android.app.Activity, android.os.Bundle), [onActivityDestroyed](http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html#onActivityDestroyed(android.app.Activity), [onActivityPaused](http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html#onActivityPaused(android.app.Activity) etc. That seems like not beautiful code. Another situation is if we want to do the same operation in every lifecycle state, we need to wrap the logic into every lifecycle state. This will make too much logic in the [ActivityLifecycleCallbacks](http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html) so that the callback class may be too heavy.

In addition, Android is not support fragment lifecycle callback natively. Lifecycle4Android provides an easy way for developer to receive the fragment lifecycle callback by to inherit the [AppLifecycleFragment](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleFragment.java). You could also receive the changed of lifecycle state from the same way that follow the library usage.

### Why to use Lifecyle4Android?

Lifecycle4Android gives you the [LifecycleAction](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/core/LifecycleAction.java) and [LifecycleListener](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/core/LifecycleListener.java) that make developer to receive the lifecycle state changed in Activity or Fragment. Developer who doesn't need to wrap the callback in every Activity/Fragment by yourself. Lifecycle4Android also provides the simple interface to add these actions and listener into the application. You only need to inherit the [AppLifecycleApplication](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleApplication.java) or to set the application name as [AppLifecycleApplication](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleApplication.java). Then, you could add the action or listener into [AppLifecycleManager](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/core/AppLifecycleManager.java) and receive the lifecycle state changed.

### Use Case

- Networking monitor
- Print the lifecycle debugging log (Lifecycle4Android has a [PrintLogAction](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/action/PrintLogAction.java) for you)
- The confirmation for alive status of application component when Activity/Fragment is started.

Another idea? Please let me know.

### How to install it?

#### Gradle

``` groovy
dependencies {
    compile 'com.meowmau.lifecycle4android:lifecycle4android:0.1.0'
}
```

### How to use it?

#### Use the default application class

1. Add the application name [AppLifecycleApplication](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleApplication.java) in the AndroidManifest.xml.
   
   ``` xml
   <application
       android:name="com.meowmau.lifecycle4android.app.AppLifecycleApplication" >
   ```
   
2. Retrieve the [AppLifecycleApplication](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleApplication.java) by casting the application context and add the implemented lifecycle action.
   
   ``` java
   public class MainActivity extends Activity {
       @Override
       protected void onCreate(Bundle savedInstanceState) {
         AppLifecycleApplication lifecycleApplication = (AppLifecycleApplication) getApplicationContext();
         lifecycleApplication.getAppLifecycleManager().addAction(/* Your customize LifecycleAction implementation */);
         super.onCreate(savedInstanceState);
       }
   }
   ```

### Use the customize application class

1. Replace the application name with your customize application class in the AndroidManifest.xml.
   
   ``` xml
   <application
       android:name=".MainApplication"
       tools:replace="name">
   ```
   
2. Inherit the [AppLifecycleApplication](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleApplication.java) and add your customize actions or listeners.
   
   ``` java
   public class MainApplication extends AppLifecycleApplication {
   
       @Override
       public void onCreate() {
           super.onCreate();
   
           final String appName = getResources().getString(R.string.app_name);
           LifecycleAction printAction = new PrintLogAction(appName);
           getAppLifecycleManager().addAction(printAction);
   
           MyAppLifecycleCallbackListener myListener = new MyAppLifecycleCallbackListener();
           getAppLifecycleManager().registerLifecycleListener(myListener);
       }
   
       @Override
       public void onTerminate() {
           super.onTerminate();
           getAppLifecycleManager().cleanup();
       }
   }
   ```
   
3. (Optional) If you want to receive the lifecycle state of fragment, you need to inherit the [AppLifecycleFragment](https://github.com/pickerweng/Lifecycle4Android/blob/master/library/src/main/java/com/meowmau/lifecycle4android/app/AppLifecycleFragment.java) class.
   
   ``` java
   public class MainActivityFragment extends AppLifecycleFragment {
   }
   ```

### License

Lifecycle4Android is under [BSD 3-clause License](http://choosealicense.com/licenses/bsd-3-clause/).

``` 
Copyright (c) 2015, Picker Weng
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of Lifecycle4Android nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```

