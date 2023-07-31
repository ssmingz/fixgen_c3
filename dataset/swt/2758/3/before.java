class PlaceHold {
  public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(DRAW);
    int nx = x;
    int ny = y;
    int nw = width;
    int nh = height;
    int naw = arcWidth;
    int nah = arcHeight;
    if (nw < 0) {
      nw = 0 - nw;
      nx = nx - nw;
    }
    if (nh < 0) {
      nh = 0 - nh;
      ny = ny - nh;
    }
    if (naw < 0) {
      naw = 0 - naw;
    }
    if (nah < 0) {
      nah = 0 - nah;
    }
    int cairo = data.cairo;
    if (cairo != 0) {
      float naw2 = naw / 2.0F;
      float nah2 = nah / 2.0F;
      float fw = nw / naw2;
      float fh = nh / nah2;
      Cairo.cairo_save(cairo);
      float offset = ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) ? 0.5F : 0.0F;
      Cairo.cairo_translate(cairo, nx + offset, ny + offset);
      Cairo.cairo_scale(cairo, naw2, nah2);
      Cairo.cairo_move_to(cairo, fw - 1, 0);
      Cairo.cairo_arc(
          cairo, fw - 1, 1, 1, Compatibility.PI + (Compatibility.PI / 2.0), Compatibility.PI * 2.0);
      Cairo.cairo_arc(cairo, fw - 1, fh - 1, 1, 0, Compatibility.PI / 2.0);
      Cairo.cairo_arc(cairo, 1, fh - 1, 1, Compatibility.PI / 2, PI);
      Cairo.cairo_arc(cairo, 1, 1, 1, PI, (270.0 * Compatibility.PI) / 180.0);
      Cairo.cairo_close_path(cairo);
      Cairo.cairo_restore(cairo);
      Cairo.cairo_stroke(cairo);
      return;
    }
    int naw2 = naw / 2;
    int nah2 = nah / 2;
    int drawable = data.drawable;
    if (nw > naw) {
      if (nh > nah) {
        OS.gdk_draw_arc(drawable, handle, 0, nx, ny, naw, nah, 5760, 5760);
        OS.gdk_draw_line(drawable, handle, nx + naw2, ny, (nx + nw) - naw2, ny);
        OS.gdk_draw_arc(drawable, handle, 0, (nx + nw) - naw, ny, naw, nah, 0, 5760);
        OS.gdk_draw_line(drawable, handle, nx + nw, ny + nah2, nx + nw, (ny + nh) - nah2);
        OS.gdk_draw_arc(
            drawable, handle, 0, (nx + nw) - naw, (ny + nh) - nah, naw, nah, 17280, 5760);
        OS.gdk_draw_line(drawable, handle, nx + naw2, ny + nh, (nx + nw) - naw2, ny + nh);
        OS.gdk_draw_arc(drawable, handle, 0, nx, (ny + nh) - nah, naw, nah, 11520, 5760);
        OS.gdk_draw_line(drawable, handle, nx, ny + nah2, nx, (ny + nh) - nah2);
      } else {
        OS.gdk_draw_arc(drawable, handle, 0, nx, ny, naw, nh, 5760, 11520);
        OS.gdk_draw_line(drawable, handle, nx + naw2, ny, (nx + nw) - naw2, ny);
        OS.gdk_draw_arc(drawable, handle, 0, (nx + nw) - naw, ny, naw, nh, 17280, 11520);
        OS.gdk_draw_line(drawable, handle, nx + naw2, ny + nh, (nx + nw) - naw2, ny + nh);
      }
    } else if (nh > nah) {
      OS.gdk_draw_arc(drawable, handle, 0, nx, ny, nw, nah, 0, 11520);
      OS.gdk_draw_line(drawable, handle, nx + nw, ny + nah2, nx + nw, (ny + nh) - nah2);
      OS.gdk_draw_arc(drawable, handle, 0, nx, (ny + nh) - nah, nw, nah, 11520, 11520);
      OS.gdk_draw_line(drawable, handle, nx, ny + nah2, nx, (ny + nh) - nah2);
    } else {
      OS.gdk_draw_arc(drawable, handle, 0, nx, ny, nw, nh, 0, 23040);
    }
  }
}
