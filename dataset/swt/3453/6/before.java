class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    GtkAdjustment adjustment = new GtkAdjustment(handle);
    adjustment.lower = ((float) (value));
    OS.memmove(handle, adjustment);
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_adjustment_changed(handle);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
