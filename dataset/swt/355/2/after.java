class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    super.setBackground(color);
    background = color;
    redraw();
  }
}
