class PlaceHold {
  public static NSArray array() {
    long result = OS.objc_msgSend(class_NSArray, sel_array);
    return result != 0 ? new NSArray(result) : null;
  }
}
