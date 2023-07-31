class PlaceHold {
  public void setThumb(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    long hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(hAdjustment, adjustment);
    value = ((int) (Math.min(value, ((int) (adjustment.upper - adjustment.lower)))));
    adjustment.page_size = ((double) (value));
    adjustment.value = Math.min(((int) (adjustment.value)), ((int) (adjustment.upper - value)));
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    gtk_adjustment_configure(hAdjustment, adjustment);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
