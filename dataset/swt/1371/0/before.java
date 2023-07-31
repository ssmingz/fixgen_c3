class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, handle, sizeof);
    return ((int) (adjustment.page_increment));
  }
}
