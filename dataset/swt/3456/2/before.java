class PlaceHold {
  public static float systemFontSize() {
    return ((float) (OS.objc_msgSend_fpret(class_NSFont, sel_systemFontSize)));
  }
}
