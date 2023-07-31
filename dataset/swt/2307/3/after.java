class FontData {
  public FontData(String name, int height, int style) {
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    data = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
    setName(name);
    setHeight(height);
    setStyle(style);
    data.lfCharSet = ((byte) (OS.DEFAULT_CHARSET));
  }
}
