class PlaceHold {
  public int getThumb() {
    checkWidget();
    GtkAdjustment adjustment = new GtkAdjustment(handle);
    return ((int) (adjustment.page_size));
  }
}
