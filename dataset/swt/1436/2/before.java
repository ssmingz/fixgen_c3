class PlaceHold {
  public static NSStatusBar systemStatusBar() {
    int result = OS.objc_msgSend(class_NSStatusBar, sel_systemStatusBar);
    return result != 0 ? new NSStatusBar(result) : null;
  }
}
