class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(adjustmentHandle, adjustment);
    int minimum = ((int) (adjustment.lower));
    if (value <= minimum) {
      return;
    }
    adjustment.upper = value;
    adjustment.page_size = Math.min(((int) (adjustment.page_size)), value - minimum);
    adjustment.value = Math.min(((int) (adjustment.value)), ((int) (value - adjustment.page_size)));
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
