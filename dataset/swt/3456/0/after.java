class PlaceHold {
  public static double scrollerWidth() {
    return ((double) (OS.objc_msgSend_fpret(class_NSScroller, sel_scrollerWidth)));
  }
}
