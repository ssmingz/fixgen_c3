class PlaceHold {
  public void setThumb(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(adjustmentHandle, adjustment);
    value = ((int) (Math.min(value, ((int) (adjustment.upper - adjustment.lower)))));
    adjustment.page_size = ((double) (value));
    adjustment.value = Math.min(((int) (adjustment.value)), ((int) (adjustment.upper - value)));
    OS.g_signal_handlers_block_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    OS.gtk_adjustment_configure(
        adjustmentHandle,
        adjustment.value,
        adjustment.lower,
        adjustment.upper,
        adjustment.step_increment,
        adjustment.page_increment,
        adjustment.page_size);
    OS.g_signal_handlers_unblock_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
