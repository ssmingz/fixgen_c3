class PlaceHold {
  public void setBorderVisible(boolean show) {
    checkWidget();
    if (showBorder == show) {
      return;
    }
    showBorder = show;
    if (showBorder) {
      borderLeft = borderTop = borderRight = borderBottom = 1;
      if ((getStyle() & SWT.FLAT) == 0) {
        highlight = 2;
      }
    } else {
      borderBottom = borderTop = borderLeft = borderRight = 0;
      highlight = 0;
    }
    layout(false);
    redraw();
  }
}
