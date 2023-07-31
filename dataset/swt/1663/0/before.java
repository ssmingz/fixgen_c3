class PlaceHold {
  void clear() {
    text = "";
    image = null;
    strings = null;
    images = null;
    imageIndent = 0;
    checked = grayed = false;
    background = foreground = font = -1;
    cellBackground = cellForeground = cellFont = null;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = false;
    }
  }
}
