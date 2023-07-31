class PlaceHold {
  public static NSSet set() {
    long result = OS.objc_msgSend(class_NSSet, sel_set);
    return result != 0 ? new NSSet(result) : null;
  }
}
