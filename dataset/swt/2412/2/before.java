class PlaceHold {
  void addColumn(TableColumn column) {
    int index = column.getIndex();
    int columnCount = parent.columns.length;
    if (columnCount > 1) {
      if (columnCount == 2) {
        texts = new String[2];
      } else {
        String[] newTexts = new String[columnCount];
        System.arraycopy(texts, 0, newTexts, 0, index);
        System.arraycopy(texts, index, newTexts, index + 1, (columnCount - index) - 1);
        texts = newTexts;
      }
      if (index == 0) {
        texts[1] = text;
        text = "";
      }
      if (columnCount == 2) {
        images = new Image[2];
      } else {
        Image[] newImages = new Image[columnCount];
        System.arraycopy(images, 0, newImages, 0, index);
        System.arraycopy(images, index, newImages, index + 1, (columnCount - index) - 1);
        images = newImages;
      }
      if (index == 0) {
        images[1] = image;
        image = null;
      }
      int[] newTextWidths = new int[columnCount];
      System.arraycopy(textWidths, 0, newTextWidths, 0, index);
      System.arraycopy(textWidths, index, newTextWidths, index + 1, (columnCount - index) - 1);
      textWidths = newTextWidths;
    }
    String[] newDisplayTexts = new String[columnCount];
    if (columnCount > 1) {
      System.arraycopy(displayTexts, 0, newDisplayTexts, 0, index);
      System.arraycopy(displayTexts, index, newDisplayTexts, index + 1, (columnCount - index) - 1);
    }
    displayTexts = newDisplayTexts;
    if (cellBackgrounds != null) {
      Color[] newCellBackgrounds = new Color[columnCount];
      System.arraycopy(cellBackgrounds, 0, newCellBackgrounds, 0, index);
      System.arraycopy(
          cellBackgrounds, index, newCellBackgrounds, index + 1, (columnCount - index) - 1);
      cellBackgrounds = newCellBackgrounds;
    }
    if (cellForegrounds != null) {
      Color[] newCellForegrounds = new Color[columnCount];
      System.arraycopy(cellForegrounds, 0, newCellForegrounds, 0, index);
      System.arraycopy(
          cellForegrounds, index, newCellForegrounds, index + 1, (columnCount - index) - 1);
      cellForegrounds = newCellForegrounds;
    }
    if (cellFonts != null) {
      Font[] newCellFonts = new Font[columnCount];
      System.arraycopy(cellFonts, 0, newCellFonts, 0, index);
      System.arraycopy(cellFonts, index, newCellFonts, index + 1, (columnCount - index) - 1);
      cellFonts = newCellFonts;
      int[] newFontHeights = new int[columnCount];
      System.arraycopy(fontHeights, 0, newFontHeights, 0, index);
      System.arraycopy(fontHeights, index, newFontHeights, index + 1, (columnCount - index) - 1);
      fontHeights = newFontHeights;
    }
    if ((index == 0) && (columnCount > 1)) {
      if ((parent.style & SWT.CHECK) != 0) {
        GC gc = new GC(parent);
        gc.setFont(getFont(1));
        computeDisplayText(1, gc);
        gc.dispose();
      }
    }
  }
}
