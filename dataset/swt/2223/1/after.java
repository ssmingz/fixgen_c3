class PlaceHold {
  public Point getPreferredSize() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return new Point(0, 0);
    }
    int hwnd = parent.handle;
    REBARBANDINFO rbBand = new REBARBANDINFO();
    rbBand.cbSize = REBARBANDINFO.sizeof;
    rbBand.fMask = OS.RBBIM_CHILDSIZE | OS.RBBIM_IDEALSIZE;
    OS.SendMessage(hwnd, RB_GETBANDINFO, index, rbBand);
    RECT rect = new RECT();
    OS.SendMessage(hwnd, RB_GETBANDBORDERS, index, rect);
    int width = (rbBand.cxIdeal + rect.left) + CoolBar.INSET;
    if ((parent.style & SWT.FLAT) == 0) {
      width += rect.right;
    }
    return new Point(width, rbBand.cyMinChild);
  }
}
