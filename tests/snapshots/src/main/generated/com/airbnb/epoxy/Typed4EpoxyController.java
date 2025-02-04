package com.airbnb.epoxy;

import android.os.Handler;
//     ^^^^^^^ reference android/
//             ^^ reference android/os/
//                ^^^^^^^ reference android/os/Handler#

/**
 * This is a wrapper around {@link com.airbnb.epoxy.EpoxyController} to simplify how data is
 * accessed. Use this if the data required to build your models is represented by four objects.
 * <p>
 * To use this, create a subclass typed with your data object. Then, call {@link #setData}
 * whenever that data changes. This class will handle calling {@link #buildModels} with the
 * latest data.
 * <p>
 * You should NOT call {@link #requestModelBuild()} directly.
 *
 * @see TypedEpoxyController
 * @see Typed2EpoxyController
 * @see Typed3EpoxyController
 */
public abstract class Typed4EpoxyController<T, U, V, W> extends EpoxyController {
//                    ^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController# public abstract class Typed4EpoxyController<T, U, V, W>
//                                          ^ definition com/airbnb/epoxy/Typed4EpoxyController#[T] T
//                                             ^ definition com/airbnb/epoxy/Typed4EpoxyController#[U] U
//                                                ^ definition com/airbnb/epoxy/Typed4EpoxyController#[V] V
//                                                   ^ definition com/airbnb/epoxy/Typed4EpoxyController#[W] W
//                                                              ^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyController#

  private T data1;
//        ^ reference com/airbnb/epoxy/Typed4EpoxyController#[T]
//          ^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#data1. private T data1
  private U data2;
//        ^ reference com/airbnb/epoxy/Typed4EpoxyController#[U]
//          ^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#data2. private U data2
  private V data3;
//        ^ reference com/airbnb/epoxy/Typed4EpoxyController#[V]
//          ^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#data3. private V data3
  private W data4;
//        ^ reference com/airbnb/epoxy/Typed4EpoxyController#[W]
//          ^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#data4. private W data4
  private boolean allowModelBuildRequests;
//                ^^^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests. private boolean allowModelBuildRequests

  public Typed4EpoxyController() {
//       ^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#`<init>`(). public Typed4EpoxyController()
  }

  public Typed4EpoxyController(Handler modelBuildingHandler, Handler diffingHandler) {
//       ^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#`<init>`(+1). public Typed4EpoxyController(unresolved_type modelBuildingHandler, unresolved_type diffingHandler)
//                             ^^^^^^^ reference _root_/
//                                     ^^^^^^^^^^^^^^^^^^^^ definition local0 unresolved_type modelBuildingHandler
//                                                           ^^^^^^^ reference _root_/
//                                                                   ^^^^^^^^^^^^^^ definition local1 unresolved_type diffingHandler
    super(modelBuildingHandler, diffingHandler);
//  ^^^^^ reference com/airbnb/epoxy/EpoxyController#`<init>`(+1).
//        ^^^^^^^^^^^^^^^^^^^^ reference local0
//                              ^^^^^^^^^^^^^^ reference local1
  }

  /**
   * Call this with the latest data when you want models to be rebuilt. The data will be passed on
   * to {@link #buildModels(Object, Object, Object, Object)}
   */
  public void setData(T data1, U data2, V data3, W data4) {
//            ^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#setData(). public void setData(T data1, U data2, V data3, W data4)
//                    ^ reference com/airbnb/epoxy/Typed4EpoxyController#[T]
//                      ^^^^^ definition local2 T data1
//                             ^ reference com/airbnb/epoxy/Typed4EpoxyController#[U]
//                               ^^^^^ definition local3 U data2
//                                      ^ reference com/airbnb/epoxy/Typed4EpoxyController#[V]
//                                        ^^^^^ definition local4 V data3
//                                               ^ reference com/airbnb/epoxy/Typed4EpoxyController#[W]
//                                                 ^^^^^ definition local5 W data4
    this.data1 = data1;
//       ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data1.
//               ^^^^^ reference local2
    this.data2 = data2;
//       ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data2.
//               ^^^^^ reference local3
    this.data3 = data3;
//       ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data3.
//               ^^^^^ reference local4
    this.data4 = data4;
//       ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data4.
//               ^^^^^ reference local5
    allowModelBuildRequests = true;
//  ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
    requestModelBuild();
//  ^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#requestModelBuild().
    allowModelBuildRequests = false;
//  ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public final void requestModelBuild() {
//                  ^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#requestModelBuild(). @Override public final void requestModelBuild()
    if (!allowModelBuildRequests) {
//       ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
      throw new IllegalStateException(
//              ^^^^^^^^^^^^^^^^^^^^^ reference java/lang/IllegalStateException#`<init>`(+1).
          "You cannot call `requestModelBuild` directly. Call `setData` instead to trigger a "
              + "model refresh with new data.");
    }
    super.requestModelBuild();
//  ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#super.
//        ^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyController#requestModelBuild().
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void moveModel(int fromPosition, int toPosition) {
//            ^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#moveModel(). @Override public void moveModel(int fromPosition, int toPosition)
//                          ^^^^^^^^^^^^ definition local6 int fromPosition
//                                            ^^^^^^^^^^ definition local7 int toPosition
    allowModelBuildRequests = true;
//  ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
    super.moveModel(fromPosition, toPosition);
//  ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#super.
//        ^^^^^^^^^ reference com/airbnb/epoxy/EpoxyController#moveModel().
//                  ^^^^^^^^^^^^ reference local6
//                                ^^^^^^^^^^ reference local7
    allowModelBuildRequests = false;
//  ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void requestDelayedModelBuild(int delayMs) {
//            ^^^^^^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#requestDelayedModelBuild(). @Override public void requestDelayedModelBuild(int delayMs)
//                                         ^^^^^^^ definition local8 int delayMs
    if (!allowModelBuildRequests) {
//       ^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#allowModelBuildRequests.
      throw new IllegalStateException(
//              ^^^^^^^^^^^^^^^^^^^^^ reference java/lang/IllegalStateException#`<init>`(+1).
          "You cannot call `requestModelBuild` directly. Call `setData` instead to trigger a "
              + "model refresh with new data.");
    }
    super.requestDelayedModelBuild(delayMs);
//  ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#super.
//        ^^^^^^^^^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyController#requestDelayedModelBuild().
//                                 ^^^^^^^ reference local8
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  protected final void buildModels() {
//                     ^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#buildModels(). @Override protected final void buildModels()
    if (!isBuildingModels()) {
//       ^^^^^^^^^^^^^^^^ reference com/airbnb/epoxy/EpoxyController#isBuildingModels().
      throw new IllegalStateException(
//              ^^^^^^^^^^^^^^^^^^^^^ reference java/lang/IllegalStateException#`<init>`(+1).
          "You cannot call `buildModels` directly. Call `setData` instead to trigger a model "
              + "refresh with new data.");
    }
    buildModels(data1, data2, data3, data4);
//  ^^^^^^^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#buildModels(+1).
//              ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data1.
//                     ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data2.
//                            ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data3.
//                                   ^^^^^ reference com/airbnb/epoxy/Typed4EpoxyController#data4.
  }

  protected abstract void buildModels(T data1, U data2, V data3, W data4);
//                        ^^^^^^^^^^^ definition com/airbnb/epoxy/Typed4EpoxyController#buildModels(+1). protected abstract void buildModels(T data1, U data2, V data3, W data4)
//                                    ^ reference com/airbnb/epoxy/Typed4EpoxyController#[T]
//                                      ^^^^^ definition local9 T data1
//                                             ^ reference com/airbnb/epoxy/Typed4EpoxyController#[U]
//                                               ^^^^^ definition local10 U data2
//                                                      ^ reference com/airbnb/epoxy/Typed4EpoxyController#[V]
//                                                        ^^^^^ definition local11 V data3
//                                                               ^ reference com/airbnb/epoxy/Typed4EpoxyController#[W]
//                                                                 ^^^^^ definition local12 W data4
}

