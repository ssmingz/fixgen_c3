class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    Rectangle trim = super.computeTrim(x, y, width, height);
    int newFont;
    int oldFont = 0;
    int hDC = OS.GetDC(handle);
    newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    if (newFont != 0) {
      oldFont = OS.SelectObject(hDC, newFont);
    }
    TEXTMETRIC tm = new TEXTMETRIC();
    OS.GetTextMetrics(hDC, tm);
    if (newFont != 0) {
      OS.SelectObject(hDC, oldFont);
    }
    OS.ReleaseDC(handle, hDC);
    int inset = 3;
    trim.x -= inset;
    trim.y -= tm.tmHeight;
    trim.width += inset * 2;
    trim.height += tm.tmHeight + inset;
    return trim;
  }
}
