class PlaceHold {
  public static NSRunLoop mainRunLoop() {
    int result = OS.objc_msgSend(class_NSRunLoop, sel_mainRunLoop);
    return result != 0 ? new NSRunLoop(result) : null;
  }
}
