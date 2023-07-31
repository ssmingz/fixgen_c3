class PlaceHold {
  public int getMinimum() {
    checkWidget();
    return ((int) (gtk_adjustment_get_lower(adjustmentHandle)));
  }
}
