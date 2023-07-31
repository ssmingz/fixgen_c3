class PlaceHold {
  public static NSNumber numberWithBool(boolean value) {
    long result = OS.objc_msgSend(class_NSNumber, sel_numberWithBool_, value);
    return result != 0 ? new NSNumber(result) : null;
  }
}
