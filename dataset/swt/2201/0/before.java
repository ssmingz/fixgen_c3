class PlaceHold {
  public void copyArea(int x, int y, int width, int height, int destX, int destY) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((width <= 0) || (height <= 0)) {
      return;
    }
    int deltaX = destX - x;
    int deltaY = destY - y;
    if ((deltaX == 0) && (deltaY == 0)) {
      return;
    }
    int xDisplay = data.display;
    int xDrawable = data.drawable;
    if (data.image == null) {
      OS.XSetGraphicsExposures(xDisplay, handle, true);
    }
    OS.XCopyArea(xDisplay, xDrawable, xDrawable, handle, x, y, width, height, destX, destY);
    if (data.image == null) {
      OS.XSetGraphicsExposures(xDisplay, handle, false);
      boolean disjoint =
          ((((destX + width) < x) || ((x + width) < destX)) || ((destY + height) < y))
              || ((y + height) < destY);
      if (disjoint) {
        OS.XClearArea(xDisplay, xDrawable, x, y, width, height, true);
      } else {
        if (deltaX != 0) {
          int newX = destX - deltaX;
          if (deltaX < 0) {
            newX = destX + width;
          }
          OS.XClearArea(xDisplay, xDrawable, newX, y, Math.abs(deltaX), height, true);
        }
        if (deltaY != 0) {
          int newY = destY - deltaY;
          if (deltaY < 0) {
            newY = destY + height;
          }
          OS.XClearArea(xDisplay, xDrawable, x, newY, width, Math.abs(deltaY), true);
        }
      }
    }
  }
}
