class PlaceHold {
  public void showColumn(TreeColumn column) {
    checkWidget();
    int x = column.getX();
    int rightX = x + column.getWidth();
    Rectangle bounds = getClientArea();
    int boundsRight = bounds.x + bounds.width;
    if ((bounds.x <= x) && (rightX <= boundsRight)) {
      return;
    }
    int absX = 0;
    for (int i = 0; i < column.getIndex(); i++) {
      absX += columns[i].width;
    }
    if (x < bounds.x) {
      horizontalOffset = absX;
    } else {
      horizontalOffset = boundsRight - absX;
    }
    getHorizontalBar().setSelection(horizontalOffset);
    redraw();
  }
}
