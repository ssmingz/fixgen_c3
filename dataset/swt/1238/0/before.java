class PlaceHold {
  public void copyArea(
      int srcX, int srcY, int width, int height, int destX, int destY, boolean paint) {
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
    long drawable = data.drawable;
    if (OS.USE_CAIRO) {
      if (data.image != null) {
        Cairo.cairo_set_source_surface(handle, data.image.surface, deltaX, deltaY);
        Cairo.cairo_rectangle(handle, destX, destY, width, height);
        Cairo.cairo_set_operator(handle, CAIRO_OPERATOR_SOURCE);
        Cairo.cairo_fill(handle);
      } else if (drawable != 0) {
        Cairo.cairo_save(handle);
        Cairo.cairo_rectangle(handle, destX, destY, width, height);
        Cairo.cairo_clip(handle);
        Cairo.cairo_translate(handle, deltaX, deltaY);
        Cairo.cairo_set_operator(handle, CAIRO_OPERATOR_SOURCE);
        Cairo.cairo_push_group(handle);
        OS.gdk_cairo_set_source_window(handle, drawable, 0, 0);
        Cairo.cairo_paint(handle);
        Cairo.cairo_pop_group_to_source(handle);
        Cairo.cairo_rectangle(handle, destX - deltaX, destY - deltaY, width, height);
        Cairo.cairo_clip(handle);
        Cairo.cairo_paint(handle);
        Cairo.cairo_restore(handle);
        if (paint) {
          long visibleRegion = OS.gdk_drawable_get_visible_region(drawable);
          GdkRectangle srcRect = new GdkRectangle();
          srcRect.x = srcX;
          srcRect.y = srcY;
          srcRect.width = width;
          srcRect.height = height;
          long copyRegion = OS.gdk_region_rectangle(srcRect);
          OS.gdk_region_intersect(copyRegion, visibleRegion);
          long invalidateRegion = OS.gdk_region_rectangle(srcRect);
          OS.gdk_region_subtract(invalidateRegion, visibleRegion);
          OS.gdk_region_offset(invalidateRegion, deltaX, deltaY);
          OS.gdk_window_invalidate_region(drawable, invalidateRegion, false);
          OS.gdk_region_destroy(visibleRegion);
          OS.gdk_region_destroy(copyRegion);
          OS.gdk_region_destroy(invalidateRegion);
        }
      }
    } else {
      if ((data.image == null) && paint) {
        OS.gdk_gc_set_exposures(handle, true);
      }
      OS.gdk_draw_drawable(drawable, handle, drawable, srcX, srcY, destX, destY, width, height);
    }
    if ((data.image == null) & paint) {
      if (!OS.USE_CAIRO) {
        OS.gdk_gc_set_exposures(handle, false);
      }
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
