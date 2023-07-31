class PlaceHold {
  public static NSThread currentThread() {
    int result = OS.objc_msgSend(class_NSThread, sel_currentThread);
    return result != 0 ? new NSThread(result) : null;
  }
}
