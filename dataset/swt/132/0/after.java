class PlaceHold {
  public Point getSize() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      new Point(0, 0);
    }
    int hwnd = parent.handle;
    RECT rect = new RECT();
    OS.SendMessage(hwnd, RB_GETRECT, index, rect);
    if (OS.COMCTL32_MAJOR >= 6) {
      MARGINS margins = new MARGINS();
      OS.SendMessage(hwnd, RB_GETBANDMARGINS, 0, margins);
      rect.left -= margins.cxLeftWidth;
      rect.right += margins.cxRightWidth;
    }
    if (!parent.isLastItemOfRow(index)) {
      rect.right += ((parent.style & SWT.FLAT) == 0) ? CoolBar.SEPARATOR_WIDTH : 0;
    }
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    if ((parent.style & SWT.VERTICAL) != 0) {
      return new Point(height, width);
    }
    return new Point(width, height);
  }
}
