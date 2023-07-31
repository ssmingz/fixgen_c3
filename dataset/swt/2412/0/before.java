class PlaceHold {
  void removeColumn(TableColumn column, int index) {
    int columnCount = parent.columns.length;
    if (columnCount == 0) {
      cellBackgrounds = cellForegrounds = null;
      displayTexts = null;
      cellFonts = null;
      fontHeights = null;
      GC gc = new GC(parent);
      recomputeTextWidths(gc);
      gc.dispose();
      return;
    }
    String[] newTexts = new String[columnCount];
    System.arraycopy(texts, 0, newTexts, 0, index);
    System.arraycopy(texts, index + 1, newTexts, index, columnCount - index);
    texts = newTexts;
    Image[] newImages = new Image[columnCount];
    System.arraycopy(images, 0, newImages, 0, index);
    System.arraycopy(images, index + 1, newImages, index, columnCount - index);
    images = newImages;
    int[] newTextWidths = new int[columnCount];
    System.arraycopy(textWidths, 0, newTextWidths, 0, index);
    System.arraycopy(textWidths, index + 1, newTextWidths, index, columnCount - index);
    textWidths = newTextWidths;
    String[] newDisplayTexts = new String[columnCount];
    System.arraycopy(displayTexts, 0, newDisplayTexts, 0, index);
    System.arraycopy(displayTexts, index + 1, newDisplayTexts, index, columnCount - index);
    displayTexts = newDisplayTexts;
    if (cellBackgrounds != null) {
      Color[] newCellBackgrounds = new Color[columnCount];
      System.arraycopy(cellBackgrounds, 0, newCellBackgrounds, 0, index);
      System.arraycopy(cellBackgrounds, index + 1, newCellBackgrounds, index, columnCount - index);
      cellBackgrounds = newCellBackgrounds;
    }
    if (cellForegrounds != null) {
      Color[] newCellForegrounds = new Color[columnCount];
      System.arraycopy(cellForegrounds, 0, newCellForegrounds, 0, index);
      System.arraycopy(cellForegrounds, index + 1, newCellForegrounds, index, columnCount - index);
      cellForegrounds = newCellForegrounds;
    }
    if (cellFonts != null) {
      Font[] newCellFonts = new Font[columnCount];
      System.arraycopy(cellFonts, 0, newCellFonts, 0, index);
      System.arraycopy(cellFonts, index + 1, newCellFonts, index, columnCount - index);
      cellFonts = newCellFonts;
      int[] newFontHeights = new int[columnCount];
      System.arraycopy(fontHeights, 0, newFontHeights, 0, index);
      System.arraycopy(fontHeights, index + 1, newFontHeights, index, columnCount - index);
      fontHeights = newFontHeights;
    }
    if (index == 0) {
      text = (texts[0] != null) ? texts[0] : "";
      texts[0] = null;
      image = images[0];
      images[0] = null;
      if ((parent.style & SWT.CHECK) != 0) {
        GC gc = new GC(parent);
        gc.setFont(getFont(0));
        computeDisplayText(0, gc);
        gc.dispose();
      }
    }
    if (columnCount < 2) {
      texts = null;
      images = null;
    }
  }
}
