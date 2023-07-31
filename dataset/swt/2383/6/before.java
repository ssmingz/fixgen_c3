class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(adjustmentHandle, adjustment);
    int maximum = ((int) (adjustment.upper));
    if (value >= maximum) {
      return;
    }
    adjustment.lower = value;
    adjustment.page_size = Math.min(((int) (adjustment.page_size)), maximum - value);
    adjustment.value = Math.max(((int) (adjustment.value)), value);
    OS.g_signal_handlers_block_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    gtk_adjustment_configure(adjustmentHandle, adjustment);
    OS.g_signal_handlers_unblock_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
