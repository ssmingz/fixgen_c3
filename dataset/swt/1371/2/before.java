class PlaceHold {
  public int getMinimum() {
    checkWidget();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, hAdjustment, sizeof);
    return ((int) (adjustment.lower));
  }
}
