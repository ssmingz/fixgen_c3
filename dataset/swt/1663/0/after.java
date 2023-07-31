class PlaceHold {
  void clear() {
    text = "";
    image = null;
    strings = null;
    images = null;
    imageIndent = 0;
    checked = grayed = false;
    font = -1;
    background = foreground = -1;
    cellFont = null;
    cellBackground = cellForeground = null;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = false;
    }
  }
}
