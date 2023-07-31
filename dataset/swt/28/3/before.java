class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    int hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, hAdjustment);
    return ((int) (adjustment.page_increment));
  }
}
