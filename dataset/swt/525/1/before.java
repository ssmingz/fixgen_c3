class PlaceHold {
  public static NSString string() {
    int result = OS.objc_msgSend(class_NSString, sel_string);
    return result != 0 ? new NSString(result) : null;
  }
}
