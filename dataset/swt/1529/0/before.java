class PlaceHold {
  public Color getBackground(int index) {
    checkWidget();
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return getBackground();
    }
    int pixel = (cellBackground != null) ? cellBackground[index] : -1;
    if (pixel == (-1)) {
      return getBackground();
    }
    return Color.win32_new(display, pixel);
  }
}
