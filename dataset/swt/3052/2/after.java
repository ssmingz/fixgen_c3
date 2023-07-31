class PlaceHold {
  void showItem(int index) {
    if ((!painted) && hooks(MeasureItem)) {
      hitTestSelection(index, 0, 0);
    }
    if (OS.SendMessage(handle, LVM_GETCOUNTPERPAGE, 0, 0) <= 0) {
      OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 1);
      if (index != OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0)) {
        OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 1);
      }
    } else {
      OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 0);
    }
  }
}
