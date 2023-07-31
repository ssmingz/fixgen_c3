class PlaceHold {
  public void copyArea(int srcX, int srcY, int width, int height, int destX, int destY) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((width <= 0) || (height <= 0)) {
      return;
    }
    int deltaX = destX - srcX;
    int deltaY = destY - srcY;
    if ((deltaX == 0) && (deltaY == 0)) {
      return;
    }
    int drawable = data.drawable;
    if (data.image == null) {
      OS.gdk_gc_set_exposures(handle, true);
    }
    OS.gdk_draw_drawable(drawable, handle, drawable, srcX, srcY, destX, destY, width, height);
    if (data.image == null) {
      OS.gdk_gc_set_exposures(handle, false);
      boolean disjoint =
          ((((destX + width) < srcX) || ((srcX + width) < destX)) || ((destY + height) < srcY))
              || ((srcY + height) < destY);
      GdkRectangle rect = new GdkRectangle();
      if (disjoint) {
        rect.x = srcX;
        rect.y = srcY;
        rect.width = width;
        rect.height = height;
        OS.gdk_window_invalidate_rect(drawable, rect, false);
      } else {
        if (deltaX != 0) {
          int newX = destX - deltaX;
          if (deltaX < 0) {
            newX = destX + width;
          }
          rect.x = newX;
          rect.y = srcY;
          rect.width = Math.abs(deltaX);
          rect.height = height;
          OS.gdk_window_invalidate_rect(drawable, rect, false);
        }
        if (deltaY != 0) {
          int newY = destY - deltaY;
          if (deltaY < 0) {
            newY = destY + height;
          }
          rect.x = srcX;
          rect.y = newY;
          rect.width = width;
          rect.height = Math.abs(deltaY);
          OS.gdk_window_invalidate_rect(drawable, rect, false);
        }
      }
    }
  }
}
