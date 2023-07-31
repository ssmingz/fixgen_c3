class PlaceHold {
  int trayItemProc(int target, int userData, int selector, int event) {
    TrayItem item = ((TrayItem) (OS.JNIGetObject(userData)));
    if (item != null) {
      return item.trayItemProc(target, userData, selector, event);
    }
    return 0;
  }
}
