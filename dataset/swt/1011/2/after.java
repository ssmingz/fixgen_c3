class PlaceHold {
  public int getMinimum() {
    checkWidget();
    return ((int) (OS.gtk_adjustment_get_lower(adjustmentHandle)));
  }
}
