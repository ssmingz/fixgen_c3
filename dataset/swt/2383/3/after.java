class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    long hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(hAdjustment, adjustment);
    int maximum = ((int) (adjustment.upper));
    if (value >= maximum) {
      return;
    }
    adjustment.lower = value;
    adjustment.page_size = Math.min(((int) (adjustment.page_size)), maximum - value);
    adjustment.value = Math.max(((int) (adjustment.value)), value);
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    OS.gtk_adjustment_configure(
        hAdjustment,
        adjustment.value,
        adjustment.lower,
        adjustment.upper,
        adjustment.step_increment,
        adjustment.page_increment,
        adjustment.page_size);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
