class PlaceHold {
  public static NSNumber numberWithInt(int value) {
    int result = OS.objc_msgSend(class_NSNumber, sel_numberWithInt_, value);
    return result != 0 ? new NSNumber(result) : null;
  }
}
