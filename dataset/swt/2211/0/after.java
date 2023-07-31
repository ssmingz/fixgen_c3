class PlaceHold {
  public void setPageIncrement(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    long hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
    double step_increment = OS.gtk_adjustment_get_step_increment(hAdjustment);
    double newValue = value;
    int digits = OS.gtk_spin_button_get_digits(handle);
    for (int i = 0; i < digits; i++) {
      newValue /= 10;
    }
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    OS.gtk_spin_button_set_increments(handle, step_increment, newValue);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
