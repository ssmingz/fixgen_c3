class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    GtkAdjustment adjustment = new GtkAdjustment(handle);
    return ((int) (adjustment.page_increment));
  }
}
