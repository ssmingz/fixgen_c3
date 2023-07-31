class PlaceHold {
  public int getThumb() {
    checkWidget();
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, handle, sizeof);
    return ((int) (adjustment.page_size));
  }
}
