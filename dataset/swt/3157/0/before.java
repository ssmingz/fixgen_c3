class PlaceHold {
  public void setPreferredSize(int width, int height) {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    width = Math.max(0, width);
    height = Math.max(0, height);
    ideal = true;
    int hwnd = parent.handle;
    RECT rect = new RECT();
    OS.SendMessage(hwnd, RB_GETBANDBORDERS, index, rect);
    REBARBANDINFO rbBand = new REBARBANDINFO();
    rbBand.cbSize = REBARBANDINFO.sizeof;
    rbBand.fMask = OS.RBBIM_CHILDSIZE;
    OS.SendMessage(hwnd, RB_GETBANDINFO, index, rbBand);
    rbBand.fMask = OS.RBBIM_CHILDSIZE | OS.RBBIM_IDEALSIZE;
    rbBand.cxIdeal = Math.max(0, ((width - rect.left) - rect.right) - CoolBar.INSET);
    rbBand.cyMaxChild = height;
    if (!minimum) {
      rbBand.cyMinChild = height;
    }
    OS.SendMessage(hwnd, RB_SETBANDINFO, index, rbBand);
  }
}
