class PlaceHold {
  int stringWidth(StyleItem run, char[] ch) {
    if (ch.length == 0) {
      return 0;
    }
    Font font = getItemFont(run);
    int fontList = font.handle;
    byte[] buffer = Converter.wcsToMbcs(font.codePage, ch, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    int width = OS.XmStringWidth(fontList, xmString);
    OS.XmStringFree(xmString);
    return width;
  }
}
