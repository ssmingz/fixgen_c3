class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return ((int) (OS.gtk_adjustment_get_upper(adjustmentHandle)));
  }
}
