class PlaceHold {
  public static NSBundle mainBundle() {
    long result = OS.objc_msgSend(class_NSBundle, sel_mainBundle);
    return result != 0 ? new NSBundle(result) : null;
  }
}
