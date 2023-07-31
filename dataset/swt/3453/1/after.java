class PlaceHold {
  public void setIncrement(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, handle);
    adjustment.step_increment = ((float) (value));
    OS.memmove(handle, adjustment);
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_adjustment_changed(handle);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
