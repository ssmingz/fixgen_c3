class PlaceHold {
  public void setDigits(int value) {
    checkWidget();
    if (value < 0) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int digits = OS.gtk_spin_button_get_digits(handle);
    if (value == digits) {
      return;
    }
    long hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(hAdjustment, adjustment);
    int diff = Math.abs(value - digits);
    int factor = 1;
    for (int i = 0; i < diff; i++) {
      factor *= 10;
    }
    if (digits > value) {
      adjustment.value *= factor;
      adjustment.upper *= factor;
      adjustment.lower *= factor;
      adjustment.step_increment *= factor;
      adjustment.page_increment *= factor;
      climbRate *= factor;
    } else {
      adjustment.value /= factor;
      adjustment.upper /= factor;
      adjustment.lower /= factor;
      adjustment.step_increment /= factor;
      adjustment.page_increment /= factor;
      climbRate /= factor;
    }
    OS.gtk_adjustment_configure(
        hAdjustment,
        adjustment.value,
        adjustment.lower,
        adjustment.upper,
        adjustment.step_increment,
        adjustment.page_increment,
        adjustment.page_size);
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
    OS.gtk_spin_button_configure(handle, hAdjustment, climbRate, value);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, VALUE_CHANGED);
  }
}
