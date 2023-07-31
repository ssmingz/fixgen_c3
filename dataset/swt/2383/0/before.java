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
    long hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    adjustment.value = Math.min(Math.max(selection, minimum), maximum - thumb);
    adjustment.lower = ((double) (minimum));
    adjustment.upper = ((double) (maximum));
    adjustment.page_size = ((double) (thumb));
    adjustment.step_increment = ((double) (increment));
    adjustment.page_increment = ((double) (pageIncrement));
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    gtk_adjustment_configure(hAdjustment, adjustment);
    OS.gtk_adjustment_value_changed(hAdjustment);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
