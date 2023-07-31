class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    super.setForeground(color);
    foreground = color;
    redraw();
  }
}
