class PlaceHold {
  public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
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
      Cairo.cairo_new_path(cairo);
      Cairo.cairo_save(cairo);
      Cairo.cairo_translate(cairo, nx, ny);
      Cairo.cairo_scale(cairo, naw2, nah2);
      Cairo.cairo_move_to(cairo, fw - 1, 0);
      Cairo.cairo_close_path(handle);
      Cairo.cairo_stroke(cairo);
      Cairo.cairo_restore(cairo);
      return;
    }
    int naw2 = naw / 2;
    int nah2 = nah / 2;
    int xDisplay = data.display;
    int xDrawable = data.drawable;
    if (nw > naw) {
      if (nh > nah) {
        OS.XDrawArc(xDisplay, xDrawable, handle, nx, ny, naw, nah, 5760, 5760);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx + naw2, ny, (nx + nw) - naw2, ny);
        OS.XDrawArc(xDisplay, xDrawable, handle, (nx + nw) - naw, ny, naw, nah, 0, 5760);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx + nw, ny + nah2, nx + nw, (ny + nh) - nah2);
        OS.XDrawArc(
            xDisplay, xDrawable, handle, (nx + nw) - naw, (ny + nh) - nah, naw, nah, 17280, 5760);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx + naw2, ny + nh, (nx + nw) - naw2, ny + nh);
        OS.XDrawArc(xDisplay, xDrawable, handle, nx, (ny + nh) - nah, naw, nah, 11520, 5760);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx, ny + nah2, nx, (ny + nh) - nah2);
      } else {
        OS.XDrawArc(xDisplay, xDrawable, handle, nx, ny, naw, nh, 5760, 11520);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx + naw2, ny, (nx + nw) - naw2, ny);
        OS.XDrawArc(xDisplay, xDrawable, handle, (nx + nw) - naw, ny, naw, nh, 17280, 11520);
        OS.XDrawLine(xDisplay, xDrawable, handle, nx + naw2, ny + nh, (nx + nw) - naw2, ny + nh);
      }
    } else if (nh > nah) {
      OS.XDrawArc(xDisplay, xDrawable, handle, nx, ny, nw, nah, 0, 11520);
      OS.XDrawLine(xDisplay, xDrawable, handle, nx + nw, ny + nah2, nx + nw, (ny + nh) - nah2);
      OS.XDrawArc(xDisplay, xDrawable, handle, nx, (ny + nh) - nah, nw, nah, 11520, 11520);
      OS.XDrawLine(xDisplay, xDrawable, handle, nx, ny + nah2, nx, (ny + nh) - nah2);
    } else {
      OS.XDrawArc(xDisplay, xDrawable, handle, nx, ny, nw, nh, 0, 23040);
    }
  }
}
