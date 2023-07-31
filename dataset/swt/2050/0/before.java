class PlaceHold {
  public static NSSet set() {
    int result = OS.objc_msgSend(class_NSMutableSet, sel_set);
    return result != 0 ? new NSMutableSet(result) : null;
  }
}
