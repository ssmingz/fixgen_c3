class PlaceHold {
  public Point computeSize(int wHint, int hHint) {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return new Point(0, 0);
    }
    int width = wHint;
    int height = hHint;
    if (wHint == SWT.DEFAULT) {
      width = 32;
    }
    if (hHint == SWT.DEFAULT) {
      height = 32;
    }
    int hwnd = parent.handle;
    RECT rect = new RECT();
    OS.SendMessage(hwnd, RB_GETBANDBORDERS, index, rect);
    width += (rect.left + rect.right) + CoolBar.INSET;
    return new Point(width, height);
  }
}
