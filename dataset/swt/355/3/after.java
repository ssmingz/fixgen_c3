class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    background = color;
    super.setBackground(color);
    super.redraw();
  }
}
