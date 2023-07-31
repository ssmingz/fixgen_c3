class PlaceHold {
  public static NSInputManager currentInputManager() {
    int result = OS.objc_msgSend(class_NSInputManager, sel_currentInputManager);
    return result != 0 ? new NSInputManager(result) : null;
  }
}
