class PlaceHold {
  public static NSRunLoop currentRunLoop() {
    int result = OS.objc_msgSend(class_NSRunLoop, sel_currentRunLoop);
    return result != 0 ? new NSRunLoop(result) : null;
  }
}
