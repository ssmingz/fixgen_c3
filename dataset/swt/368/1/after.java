class PlaceHold {
  void clear() {
    checked = grayed = false;
    texts = null;
    int[] textWidths = new int[1];
    fontHeight = 0;
    fontHeights = null;
    images = null;
    foreground = background = null;
    displayTexts = null;
    cellForegrounds = cellBackgrounds = null;
    font = null;
    cellFonts = null;
    cached = false;
    text = "";
    image = null;
    int columnCount = parent.columns.length;
    if (columnCount > 0) {
      displayTexts = new String[columnCount];
      if (columnCount > 1) {
        texts = new String[columnCount];
        textWidths = new int[columnCount];
        images = new Image[columnCount];
      }
    }
  }
}
