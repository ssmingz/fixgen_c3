class PlaceHold {
  public void setBorderVisible(boolean show) {
    if (showBorders == show) {
      return;
    }
    showBorders = show;
    if (showBorders) {
      if ((getStyle() & SWT.FLAT) != 0) {
        borderBottom = borderTop = borderLeft = borderRight = 1;
      } else {
        borderLeft = borderTop = 1;
        borderRight = borderBottom = 3;
      }
    } else {
      borderBottom = borderTop = borderLeft = borderRight = 0;
    }
    onClientAreaChange();
  }
}
