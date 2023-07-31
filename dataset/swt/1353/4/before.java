class PlaceHold {
  void parseChar(char c) {
    if (c == 0) {
      return;
    }
    if (c == ';') {
      if (creatingFontTable) {
        fontName = wordBuffer.toString();
        wordBuffer = new StringBuffer();
        creatingFontTable = false;
        return;
      }
      if (creatingColorTable) {
        colorTable.addElement(new Color(printer, red, green, blue));
        red = green = blue = 0;
        return;
      }
    }
    if (c != '\t') {
      wordBuffer.append(c);
    }
    if ((!Character.isLetterOrDigit(c)) && (!creatingFontTable)) {
      printWordBuffer();
      if (c == '\t') {
        x += tabWidth;
      }
    }
  }
}
