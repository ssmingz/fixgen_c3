class PlaceHold {
  int getCodePage() {
    int[] lpCs = new int[8];
    int cs = OS.GetTextCharset(handle);
    OS.TranslateCharsetInfo(cs, lpCs, TCI_SRCCHARSET);
    return lpCs[1];
  }
}
