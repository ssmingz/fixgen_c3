class PlaceHold {
  public static NSArray array() {
    int result = OS.objc_msgSend(class_NSMutableArray, sel_array);
    return result != 0 ? new NSArray(result) : null;
  }
}
