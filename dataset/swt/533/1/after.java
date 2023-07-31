class PlaceHold {
  public Color getForeground(int index) {
    checkWidget();
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return getForeground();
    }
    int pixel = (cellForeground != null) ? cellForeground[index] : -1;
    return pixel == (-1) ? getForeground() : Color.win32_new(display, pixel);
  }
}
