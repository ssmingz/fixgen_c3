class PlaceHold {
  public void setSize(int width, int height) {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    width = Math.max(0, width);
    height = Math.max(0, height);
    int hwnd = parent.handle;
    REBARBANDINFO rbBand = new REBARBANDINFO();
    rbBand.cbSize = REBARBANDINFO.sizeof;
    int count = OS.SendMessage(hwnd, RB_GETBANDCOUNT, 0, 0);
    boolean isLastItem;
    if ((index + 1) == count) {
      isLastItem = true;
    } else {
      rbBand.fMask = OS.RBBIM_STYLE;
      OS.SendMessage(hwnd, RB_GETBANDINFO, index + 1, rbBand);
      isLastItem = (rbBand.fStyle & OS.RBBS_BREAK) != 0;
      rbBand.fMask = 0;
    }
    rbBand.fMask = OS.RBBIM_CHILDSIZE | OS.RBBIM_IDEALSIZE;
    OS.SendMessage(hwnd, RB_GETBANDINFO, index, rbBand);
    rbBand.fMask = OS.RBBIM_CHILDSIZE | OS.RBBIM_IDEALSIZE;
    if (!ideal) {
      RECT rect = new RECT();
      OS.SendMessage(hwnd, RB_GETBANDBORDERS, index, rect);
      rbBand.cxIdeal = Math.max(0, ((width - rect.left) - rect.right) - CoolBar.INSET);
    }
    if (!minimum) {
      rbBand.cyMinChild = height;
    }
    rbBand.cyChild = rbBand.cyMaxChild = height;
    if (!isLastItem) {
      rbBand.cx = width - 2;
      rbBand.fMask |= OS.RBBIM_SIZE;
    }
    OS.SendMessage(hwnd, RB_SETBANDINFO, index, rbBand);
  }
}
