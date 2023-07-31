class PlaceHold {
  public void setLineWidth(int width) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int xDisplay = data.display;
    XGCValues values = new XGCValues();
    OS.XGetGCValues(xDisplay, handle, (OS.GCLineWidth | OS.GCCapStyle) | OS.GCJoinStyle, values);
    int line_style = (data.lineStyle == SWT.LINE_SOLID) ? OS.LineSolid : OS.LineOnOffDash;
    OS.XSetLineAttributes(
        data.display, handle, width, line_style, values.cap_style, values.join_style);
    int cairo = data.cairo;
    if (cairo != 0) {
      Cairo.cairo_set_line_width(cairo, Math.max(1, width));
    }
  }
}
