class PlaceHold {
  public static double systemFontSize() {
    return ((double) (OS.objc_msgSend_fpret(class_NSFont, sel_systemFontSize)));
  }
}
