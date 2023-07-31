class PlaceHold {
  public int getMinimum() {
    checkWidget();
    int hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, hAdjustment);
    int digits = OS.gtk_spin_button_get_digits(handle);
    for (int i = 0; i < digits; i++) {
      adjustment.lower *= 10;
    }
    return ((int) (adjustment.lower));
  }
}
