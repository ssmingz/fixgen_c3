class PlaceHold {
  public FontData open() {
    int parentHandle = 0;
    if ((parent != null) && OS.PtWidgetIsRealized(shellHandle)) {
      parentHandle = parent.shellHandle;
    }
    byte[] title = null;
    if (this.title != null) {
      title = Converter.wcsToMbcs(null, this.title, true);
    }
    byte[] font = null;
    if (fontData != null) {
      if (fontData.stem != null) {
        font = fontData.stem;
      } else {
        byte[] description = Converter.wcsToMbcs(null, fontData.getName(), true);
        int osStyle = 0;
        int style = fontData.getStyle();
        if ((style & SWT.BOLD) != 0) {
          osStyle |= OS.PF_STYLE_BOLD;
        }
        if ((style & SWT.ITALIC) != 0) {
          osStyle |= OS.PF_STYLE_ITALIC;
        }
        int size = fontData.getHeight();
        font = OS.PfGenerateFontName(description, osStyle, size, new byte[OS.MAX_FONT_TAG]);
      }
      fontData = null;
    }
    int flags = OS.PHFONT_ALL_FONTS | OS.PHFONT_DONT_SHOW_LEGACY;
    int fontPtr =
        OS.PtFontSelection(parentHandle, null, title, font, PHFONT_ALL_SYMBOLS, flags, null);
    if (fontPtr != 0) {
      int length = OS.strlen(fontPtr);
      font = new byte[length];
      OS.memmove(font, fontPtr, length);
      fontData = FontData.photon_new(font);
    }
    return fontData;
  }
}
