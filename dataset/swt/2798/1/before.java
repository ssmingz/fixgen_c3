class FontData {
  public FontData(String name, int height, int style) {
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    data = new LOGFONT();
    setName(name);
    setHeight(height);
    setStyle(style);
    data.lfCharSet = OS.DEFAULT_CHARSET;
  }
}
