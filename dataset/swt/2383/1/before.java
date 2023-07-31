class PlaceHold {
  public void setValues(
      int selection, int minimum, int maximum, int thumb, int increment, int pageIncrement) {
    checkWidget();
    if (minimum < 0) {
      return;
    }
    if (maximum < 0) {
      return;
    }
    if (thumb < 1) {
      return;
    }
    if (increment < 1) {
      return;
    }
    if (pageIncrement < 1) {
      return;
    }
    thumb = Math.min(thumb, maximum - minimum);
    GtkAdjustment adjustment = new GtkAdjustment();
    adjustment.lower = minimum;
    adjustment.upper = maximum;
    adjustment.step_increment = increment;
    adjustment.page_increment = pageIncrement;
    adjustment.page_size = thumb;
    adjustment.value = Math.min(Math.max(selection, minimum), maximum - thumb);
    OS.g_signal_handlers_block_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    gtk_adjustment_configure(adjustmentHandle, adjustment);
    OS.gtk_adjustment_value_changed(adjustmentHandle);
    OS.g_signal_handlers_unblock_matched(
        adjustmentHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
