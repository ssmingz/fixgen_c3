class PlaceHold {
  int getCodePage() {
    if (OS.IsWinCE) {
      return OS.GetACP();
    }
    int[] lpCs = new int[8];
    int cs = OS.GetTextCharset(handle);
    OS.TranslateCharsetInfo(cs, lpCs, TCI_SRCCHARSET);
    return lpCs[1];
  }
}
