package com.airbnb.epoxy;

import android.os.Build;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^ reference android/os/Build#
import android.os.Handler;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^^ reference android/os/Handler#
import android.os.Handler.Callback;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^^ reference android/os/Handler/
//                        ^^^^^^^^ reference android/os/Handler/Callback#
import android.os.HandlerThread;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^^^^^^^^ reference android/os/HandlerThread#
import android.os.Looper;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^ reference android/os/Looper#
import android.os.Message;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^^ reference android/os/Message#

import androidx.annotation.MainThread;
//     ^^^^^^^^ reference androidx/
//              ^^^^^^^^^^ reference androidx/annotation/
//                         ^^^^^^^^^^ reference androidx/annotation/MainThread#

/**
 * Various helpers for running Epoxy operations off the main thread.
 */
public final class EpoxyAsyncUtil {
//                 ^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil# public final class EpoxyAsyncUtil
  private EpoxyAsyncUtil() {
//        ^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#`<init>`(). private EpoxyAsyncUtil()
  }

  /**
   * A Handler class that uses the main thread's Looper.
   */
  public static final Handler MAIN_THREAD_HANDLER =
//                    ^^^^^^^ reference _root_/
//                            ^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#MAIN_THREAD_HANDLER. public static final unresolved_type MAIN_THREAD_HANDLER
      createHandler(Looper.getMainLooper(), false);
//    ^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#createHandler().
//                  ^^^^^^ reference _root_/
//                         ^^^^^^^^^^^^^ reference getMainLooper#

  /**
   * A Handler class that uses the main thread's Looper. Additionally, this handler calls
   * {@link Message#setAsynchronous(boolean)} for
   * each {@link Message} that is sent to it or {@link Runnable} that is posted to it
   */
  public static final Handler AYSNC_MAIN_THREAD_HANDLER =
//                    ^^^^^^^ reference _root_/
//                            ^^^^^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#AYSNC_MAIN_THREAD_HANDLER. public static final unresolved_type AYSNC_MAIN_THREAD_HANDLER
      createHandler(Looper.getMainLooper(), true);
//    ^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#createHandler().
//                  ^^^^^^ reference _root_/
//                         ^^^^^^^^^^^^^ reference getMainLooper#

  private static Handler asyncBackgroundHandler;
//               ^^^^^^^ reference _root_/
//                       ^^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#asyncBackgroundHandler. private static unresolved_type asyncBackgroundHandler

  /**
   * A Handler class that uses a separate background thread dedicated to Epoxy. Additionally,
   * this handler calls {@link Message#setAsynchronous(boolean)} for
   * each {@link Message} that is sent to it or {@link Runnable} that is posted to it
   */
  @MainThread
// ^^^^^^^^^^ reference androidx/annotation/MainThread#
  public static Handler getAsyncBackgroundHandler() {
//              ^^^^^^^ reference _root_/
//                      ^^^^^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#getAsyncBackgroundHandler(). @MainThread public static unresolved_type getAsyncBackgroundHandler()
    // This is initialized lazily so we don't create the thread unless it will be used.
    // It isn't synchronized so it should only be accessed on the main thread.
    if (asyncBackgroundHandler == null) {
//      ^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#asyncBackgroundHandler.
      asyncBackgroundHandler = createHandler(buildBackgroundLooper("epoxy"), true);
//    ^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#asyncBackgroundHandler.
//                             ^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#createHandler().
//                                           ^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#buildBackgroundLooper().
    }

    return asyncBackgroundHandler;
//         ^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyAsyncUtil#asyncBackgroundHandler.
  }

  /**
   * Create a Handler with the given Looper
   *
   * @param async If true the Handler will calls {@link Message#setAsynchronous(boolean)} for
   *              each {@link Message} that is sent to it or {@link Runnable} that is posted to it.
   */
  public static Handler createHandler(Looper looper, boolean async) {
//              ^^^^^^^ reference _root_/
//                      ^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#createHandler(). public static unresolved_type createHandler(unresolved_type looper, boolean async)
//                                    ^^^^^^ reference _root_/
//                                           ^^^^^^ definition local0 unresolved_type looper
//                                                           ^^^^^ definition local1 boolean async
    if (!async) {
//       ^^^^^ reference local1
      return new Handler(looper);
//                       ^^^^^^ reference local0
    }

    // Standard way of exposing async handler on older api's from the support library
    // https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/core
    // /src/main/java/androidx/core/os/HandlerCompat.java#51
    if (Build.VERSION.SDK_INT >= 28) {
//      ^^^^^ reference Build/
//            ^^^^^^^ reference Build/VERSION#
//                    ^^^^^^^ reference Build/VERSION#SDK_INT#
      return Handler.createAsync(looper);
//           ^^^^^^^ reference _root_/
//                   ^^^^^^^^^^^ reference createAsync#
//                               ^^^^^^ reference local0
    }
    if (Build.VERSION.SDK_INT >= 16) {
//      ^^^^^ reference Build/
//            ^^^^^^^ reference Build/VERSION#
//                    ^^^^^^^ reference Build/VERSION#SDK_INT#
      try {
        //noinspection JavaReflectionMemberAccess
        return Handler.class.getDeclaredConstructor(Looper.class, Callback.class, boolean.class)
//             ^^^^^^^ reference _root_/
//                     ^^^^^ reference class#
//                           ^^^^^^^^^^^^^^^^^^^^^^ reference class#getDeclaredConstructor#
//                                                  ^^^^^^ reference _root_/
//                                                         ^^^^^ reference class#
//                                                                ^^^^^^^^ reference _root_/
//                                                                         ^^^^^ reference class#
//                                                                                        ^^^^^ reference boolean#class.
            .newInstance(looper, null, true);
//           ^^^^^^^^^^^ reference `<any>`#newInstance#
//                       ^^^^^^ reference local0
      } catch (Throwable ignored) {
//             ^^^^^^^^^ reference java/lang/Throwable#
//                       ^^^^^^^ definition local2 Throwable ignored
      }
    }

    return new Handler(looper);
//                     ^^^^^^ reference local0
  }

  /**
   * Create a new looper that runs on a new background thread.
   */
  public static Looper buildBackgroundLooper(String threadName) {
//              ^^^^^^ reference _root_/
//                     ^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/EpoxyAsyncUtil#buildBackgroundLooper(). public static unresolved_type buildBackgroundLooper(String threadName)
//                                           ^^^^^^ reference java/lang/String#
//                                                  ^^^^^^^^^^ definition local3 String threadName
    HandlerThread handlerThread = new HandlerThread(threadName);
//  ^^^^^^^^^^^^^ reference _root_/
//                ^^^^^^^^^^^^^ definition local4 unresolved_type handlerThread
//                                                  ^^^^^^^^^^ reference local3
    handlerThread.start();
//  ^^^^^^^^^^^^^ reference local4
//                ^^^^^ reference start#
    return handlerThread.getLooper();
//         ^^^^^^^^^^^^^ reference local4
//                       ^^^^^^^^^ reference getLooper#
  }
}
