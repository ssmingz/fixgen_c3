class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    if (getMaximized()) {
      Rectangle rect = getBounds();
      boolean sameOrigin = (!move) || ((rect.x == x) && (rect.y == y));
      boolean sameExtent = (!resize) || ((rect.width == width) && (rect.height == height));
      if (sameOrigin && sameExtent) {
        return 0;
      }
      setMaximized(false);
    }
    int result = 0;
    if (move) {
      int[] x_pos = new int[1];
      int[] y_pos = new int[1];
      OS.gtk_window_get_position(shellHandle, x_pos, y_pos);
      OS.gtk_window_move(shellHandle, x, y);
      if ((x_pos[0] != x) || (y_pos[0] != y)) {
        moved = true;
        oldX = x;
        oldY = y;
        sendEvent(Move);
        if (isDisposed()) {
          return 0;
        }
        result |= MOVED;
      }
    }
    if (resize) {
      width = Math.max(1, Math.max(minWidth, width - trimWidth()));
      height = Math.max(1, Math.max(minHeight, height - trimHeight()));
      OS.gtk_window_resize(shellHandle, width, height);
      boolean changed = (width != oldWidth) || (height != oldHeight);
      if (changed) {
        oldWidth = width;
        oldHeight = height;
        result |= RESIZED;
      }
      resizeBounds(width, height, changed);
    }
    return result;
  }
}
