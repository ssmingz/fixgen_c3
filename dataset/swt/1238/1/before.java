class PlaceHold {
  public void scroll(int destX, int destY, int x, int y, int width, int height, boolean all) {
    checkWidget();
    if ((width <= 0) || (height <= 0)) {
      return;
    }
    if ((style & SWT.MIRRORED) != 0) {
      int clientWidth = getClientWidth();
      x = (clientWidth - width) - x;
      destX = (clientWidth - width) - destX;
    }
    int deltaX = destX - x;
    int deltaY = destY - y;
    if ((deltaX == 0) && (deltaY == 0)) {
      return;
    }
    if (!isVisible()) {
      return;
    }
    boolean isFocus = (caret != null) && caret.isFocusCaret();
    if (isFocus) {
      caret.killFocus();
    }
    long window = paintWindow();
    long visibleRegion = OS.gdk_drawable_get_visible_region(window);
    GdkRectangle srcRect = new GdkRectangle();
    srcRect.x = x;
    srcRect.y = y;
    srcRect.width = width;
    srcRect.height = height;
    long copyRegion = OS.gdk_region_rectangle(srcRect);
    OS.gdk_region_intersect(copyRegion, visibleRegion);
    long invalidateRegion = OS.gdk_region_rectangle(srcRect);
    OS.gdk_region_subtract(invalidateRegion, visibleRegion);
    OS.gdk_region_offset(invalidateRegion, deltaX, deltaY);
    GdkRectangle copyRect = new GdkRectangle();
    OS.gdk_region_get_clipbox(copyRegion, copyRect);
    if ((copyRect.width != 0) && (copyRect.height != 0)) {
      update();
    }
    Control control = findBackgroundControl();
    if (control == null) {
      control = this;
    }
    if (control.backgroundImage != null) {
      redrawWidget(x, y, width, height, false, false, false);
      redrawWidget(destX, destY, width, height, false, false, false);
    } else {
      if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
        OS.gdk_window_invalidate_rect(window, copyRect, true);
        long cairo = OS.gdk_cairo_create(window);
        OS.gdk_cairo_set_source_window(cairo, window, 0, 0);
        Cairo.cairo_rectangle(
            cairo, copyRect.x + deltaX, copyRect.y + deltaY, copyRect.width, copyRect.height);
        Cairo.cairo_fill(cairo);
        Cairo.cairo_destroy(cairo);
      } else {
        long gdkGC = OS.gdk_gc_new(window);
        OS.gdk_gc_set_exposures(gdkGC, true);
        OS.gdk_draw_drawable(
            window,
            gdkGC,
            window,
            copyRect.x,
            copyRect.y,
            copyRect.x + deltaX,
            copyRect.y + deltaY,
            copyRect.width,
            copyRect.height);
        OS.g_object_unref(gdkGC);
      }
      boolean disjoint =
          ((((destX + width) < x) || ((x + width) < destX)) || ((destY + height) < y))
              || ((y + height) < destY);
      if (disjoint) {
        GdkRectangle rect = new GdkRectangle();
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
        OS.gdk_region_union_with_rect(invalidateRegion, rect);
      } else {
        GdkRectangle rect = new GdkRectangle();
        if (deltaX != 0) {
          int newX = destX - deltaX;
          if (deltaX < 0) {
            newX = destX + width;
          }
          rect.x = newX;
          rect.y = y;
          rect.width = Math.abs(deltaX);
          rect.height = height;
          OS.gdk_region_union_with_rect(invalidateRegion, rect);
        }
        if (deltaY != 0) {
          int newY = destY - deltaY;
          if (deltaY < 0) {
            newY = destY + height;
          }
          rect.x = x;
          rect.y = newY;
          rect.width = width;
          rect.height = Math.abs(deltaY);
          OS.gdk_region_union_with_rect(invalidateRegion, rect);
        }
      }
      OS.gdk_window_invalidate_region(window, invalidateRegion, all);
      OS.gdk_region_destroy(visibleRegion);
      OS.gdk_region_destroy(copyRegion);
      OS.gdk_region_destroy(invalidateRegion);
    }
    if (all) {
      Control[] children = _getChildren();
      for (int i = 0; i < children.length; i++) {
        Control child = children[i];
        Rectangle rect = child.getBounds();
        if ((Math.min(x + width, rect.x + rect.width) >= Math.max(x, rect.x))
            && (Math.min(y + height, rect.y + rect.height) >= Math.max(y, rect.y))) {
          child.setLocation(rect.x + deltaX, rect.y + deltaY);
        }
      }
    }
    if (isFocus) {
      caret.setFocus();
    }
  }
}
