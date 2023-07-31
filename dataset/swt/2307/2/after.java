class FontData {
  public FontData() {
    data = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
    data.lfCharSet = ((byte) (OS.DEFAULT_CHARSET));
    height = 12;
  }
}
