class PlaceHold {
  int trayItemProc(int target, int userData, int selector, int event) {
    if (userData == 0) {
      return 0;
    }
    TrayItem item = ((TrayItem) (OS.JNIGetObject(userData)));
    if (item != null) {
      return item.trayItemProc(target, userData, selector, event);
    }
    return 0;
  }
}
