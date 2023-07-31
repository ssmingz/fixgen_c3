class PlaceHold {
  public static NSNumber numberWithDouble(double value) {
    int result = OS.objc_msgSend(class_NSNumber, sel_numberWithDouble_, value);
    return result != 0 ? new NSNumber(result) : null;
  }
}
