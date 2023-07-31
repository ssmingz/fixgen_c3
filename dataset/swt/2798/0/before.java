class PlaceHold {
  void saveIMEFont() {
    if (!OS.IsDBLocale) {
      return;
    }
    if (oldFont != null) {
      return;
    }
    int hwnd = parent.handle;
    int hIMC = OS.ImmGetContext(hwnd);
    oldFont = new LOGFONT();
    if (OS.ImmGetCompositionFont(hIMC, oldFont)) {
      oldFont = null;
    }
    OS.ImmReleaseContext(hwnd, hIMC);
  }
}
