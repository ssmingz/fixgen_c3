class PlaceHold {
  public int getIncrement() {
    checkWidget();
    long hAdjustment = OS.gtk_range_get_adjustment(handle);
    return ((int) (OS.gtk_adjustment_get_step_increment(hAdjustment)));
  }
}
