class PlaceHold {
  public int getIncrement() {
    checkWidget();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment(hAdjustment);
    return ((int) (adjustment.step_increment));
  }
}
