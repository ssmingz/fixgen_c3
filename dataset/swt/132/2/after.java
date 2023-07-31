class PlaceHold {
  public Point getMinimumSize() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return new Point(0, 0);
    }
    int hwnd = parent.handle;
    REBARBANDINFO rbBand = new REBARBANDINFO();
    rbBand.cbSize = REBARBANDINFO.sizeof;
    rbBand.fMask = OS.RBBIM_CHILDSIZE;
    OS.SendMessage(hwnd, RB_GETBANDINFO, index, rbBand);
    if ((parent.style & SWT.VERTICAL) != 0) {
      return new Point(rbBand.cyMinChild, rbBand.cxMinChild);
    }
    return new Point(rbBand.cxMinChild, rbBand.cyMinChild);
  }
}
