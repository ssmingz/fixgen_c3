class PlaceHold {
  public void setPageIncrement(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, hAdjustment, sizeof);
    adjustment.page_increment = ((float) (value));
    OS.memmove(hAdjustment, adjustment, sizeof);
    OS.gtk_signal_handler_block_by_data(hAdjustment, Selection);
    OS.gtk_adjustment_changed(hAdjustment);
    OS.gtk_signal_handler_unblock_by_data(hAdjustment, Selection);
  }
}
