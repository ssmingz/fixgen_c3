class PlaceHold {
  int stringWidth(StyleItem run, char[] ch) {
    if (ch.length == 0) {
      return 0;
    }
    int fontList = getItemFont(run).handle;
    byte[] buffer = Converter.wcsToMbcs(null, ch, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    int width = OS.XmStringWidth(fontList, xmString);
    OS.XmStringFree(xmString);
    return width;
  }
}
