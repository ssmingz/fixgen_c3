class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    long hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
    double lower = OS.gtk_adjustment_get_lower(hAdjustment);
    double newValue = value;
    int digits = OS.gtk_spin_button_get_digits(handle);
    for (int i = 0; i < digits; i++) {
      newValue /= 10;
    }
    if (newValue < lower) {
      return;
    }
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    OS.gtk_spin_button_set_range(handle, lower, newValue);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
