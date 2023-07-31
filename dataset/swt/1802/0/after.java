class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    return ((int) (OS.gtk_adjustment_get_page_increment(adjustmentHandle)));
  }
}
