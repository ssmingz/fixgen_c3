class PlaceHold {
  public int getSelection() {
    checkWidget();
    return ((int) (gtk_adjustment_get_value(adjustmentHandle)));
  }
}
