class PlaceHold {
  public int getMaximum() {
    checkWidget();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment(hAdjustment);
    return ((int) (adjustment.upper));
  }
}
