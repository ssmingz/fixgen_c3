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
    int width = rbBand.cxIdeal + parent.getMargin(index);
    if ((parent.style & SWT.VERTICAL) != 0) {
      return new Point(rbBand.cyMaxChild, width);
    }
    return new Point(width, rbBand.cyMaxChild);
  }
}
