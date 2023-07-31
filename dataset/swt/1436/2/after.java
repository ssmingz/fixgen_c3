class PlaceHold {
  public static NSStatusBar systemStatusBar() {
    long result = OS.objc_msgSend(class_NSStatusBar, sel_systemStatusBar);
    return result != 0 ? new NSStatusBar(result) : null;
  }
}
