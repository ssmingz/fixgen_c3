class PlaceHold {
  int getCodePage() {
    if (OS.IsUnicode) {
      return OS.CP_ACP;
    }
    int hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    LOGFONT logFont = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
    OS.GetObject(hFont, sizeof, logFont);
    int cs = logFont.lfCharSet & 0xff;
    int[] lpCs = new int[8];
    if (OS.TranslateCharsetInfo(cs, lpCs, TCI_SRCCHARSET)) {
      return lpCs[1];
    }
    return OS.GetACP();
  }
}
