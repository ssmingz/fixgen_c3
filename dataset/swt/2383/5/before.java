class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    long hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(hAdjustment, adjustment);
    int minimum = ((int) (adjustment.lower));
    if (value <= minimum) {
      return;
    }
    adjustment.upper = value;
    adjustment.page_size = Math.min(((int) (adjustment.page_size)), value - minimum);
    adjustment.value = Math.min(((int) (adjustment.value)), ((int) (value - adjustment.page_size)));
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    gtk_adjustment_configure(hAdjustment, adjustment);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
