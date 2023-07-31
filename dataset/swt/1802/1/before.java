class PlaceHold {
  public int getIncrement() {
    checkWidget();
    return ((int) (gtk_adjustment_get_step_increment(adjustmentHandle)));
  }
}
