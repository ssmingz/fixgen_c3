class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return ((int) (gtk_adjustment_get_upper(adjustmentHandle)));
  }
}
