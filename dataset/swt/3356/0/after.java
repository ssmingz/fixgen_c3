class PlaceHold {
  public static id decimalDigitCharacterSet() {
    long result = OS.objc_msgSend(class_NSCharacterSet, sel_decimalDigitCharacterSet);
    return result != 0 ? new id(result) : null;
  }
}
