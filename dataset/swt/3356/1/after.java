class PlaceHold {
  public static NSDate distantFuture() {
    long result = OS.objc_msgSend(class_NSDate, sel_distantFuture);
    return result != 0 ? new NSDate(result) : null;
  }
}
