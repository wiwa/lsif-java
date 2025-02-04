package com.airbnb.epoxy;

import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
//     ^^^^^^^^ reference androidx/
//              ^^^^^^^^^^^^ reference androidx/recyclerview/
//                           ^^^^^^ reference androidx/recyclerview/widget/
//                                  ^^^^^^^^^^^^ reference androidx/recyclerview/widget/RecyclerView/
//                                               ^^^^^^^^^^^^^^^^^^^ reference androidx/recyclerview/widget/RecyclerView/AdapterDataObserver#

/**
 * We don't allow any data change notifications except the ones done though diffing. Forcing
 * changes to happen through diffing reduces the chance for developer error when implementing an
 * adapter.
 * <p>
 * This observer throws upon any changes done outside of diffing.
 */
class NotifyBlocker extends AdapterDataObserver {
//    ^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker# class NotifyBlocker
//    ^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#`<init>`(). NotifyBlocker()
//                          ^^^^^^^^^^^^^^^^^^^ reference _root_/

  private boolean changesAllowed;
//                ^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#changesAllowed. private boolean changesAllowed

  void allowChanges() {
//     ^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#allowChanges(). void allowChanges()
    changesAllowed = true;
//  ^^^^^^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#changesAllowed.
  }

  void blockChanges() {
//     ^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#blockChanges(). void blockChanges()
    changesAllowed = false;
//  ^^^^^^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#changesAllowed.
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onChanged() {
//            ^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onChanged(). @Override public void onChanged()
    if (!changesAllowed) {
//       ^^^^^^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#changesAllowed.
      throw new IllegalStateException(
//              ^^^^^^^^^^^^^^^^^^^^^ reference java/lang/IllegalStateException#`<init>`(+1).
          "You cannot notify item changes directly. Call `requestModelBuild` instead.");
    }
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onItemRangeChanged(int positionStart, int itemCount) {
//            ^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onItemRangeChanged(). @Override public void onItemRangeChanged(int positionStart, int itemCount)
//                                   ^^^^^^^^^^^^^ definition local0 int positionStart
//                                                      ^^^^^^^^^ definition local1 int itemCount
    onChanged();
//  ^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#onChanged().
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
//            ^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onItemRangeChanged(+1). @Override public void onItemRangeChanged(int positionStart, int itemCount, Object payload)
//                                   ^^^^^^^^^^^^^ definition local2 int positionStart
//                                                      ^^^^^^^^^ definition local3 int itemCount
//                                                                 ^^^^^^ reference java/lang/Object#
//                                                                        ^^^^^^^ definition local4 Object payload
    onChanged();
//  ^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#onChanged().
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onItemRangeInserted(int positionStart, int itemCount) {
//            ^^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onItemRangeInserted(). @Override public void onItemRangeInserted(int positionStart, int itemCount)
//                                    ^^^^^^^^^^^^^ definition local5 int positionStart
//                                                       ^^^^^^^^^ definition local6 int itemCount
    onChanged();
//  ^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#onChanged().
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onItemRangeRemoved(int positionStart, int itemCount) {
//            ^^^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onItemRangeRemoved(). @Override public void onItemRangeRemoved(int positionStart, int itemCount)
//                                   ^^^^^^^^^^^^^ definition local7 int positionStart
//                                                      ^^^^^^^^^ definition local8 int itemCount
    onChanged();
//  ^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#onChanged().
  }

  @Override
// ^^^^^^^^ reference java/lang/Override#
  public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
//            ^^^^^^^^^^^^^^^^ definition com/airbnb/epoxy/NotifyBlocker#onItemRangeMoved(). @Override public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount)
//                                 ^^^^^^^^^^^^ definition local9 int fromPosition
//                                                   ^^^^^^^^^^ definition local10 int toPosition
//                                                                   ^^^^^^^^^ definition local11 int itemCount
    onChanged();
//  ^^^^^^^^^ reference com/airbnb/epoxy/NotifyBlocker#onChanged().
  }
}
