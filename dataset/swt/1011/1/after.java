class PlaceHold {
  public int getSelection() {
    checkWidget();
    return ((int) (OS.gtk_adjustment_get_value(adjustmentHandle)));
  }
}
