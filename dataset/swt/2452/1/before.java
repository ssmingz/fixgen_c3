class PlaceHold {
  boolean drawCaret() {
    if (parent == null) {
      return false;
    }
    if (parent.isDisposed()) {
      return false;
    }
    int handle = parent.handle;
    if (!OS.PtWidgetIsRealized(handle)) {
      return false;
    }
    int phGC = OS.PgCreateGC(0);
    if (phGC == 0) {
      return false;
    }
    int[] args = new int[] {OS.Pt_ARG_COLOR, 0, 0, OS.Pt_ARG_FILL_COLOR, 0, 0};
    OS.PtGetResources(handle, args.length / 3, args);
    int foreground = args[1];
    int background = args[4];
    int color = foreground ^ (~background);
    int prevContext = OS.PgSetGC(phGC);
    OS.PgSetRegion(OS.PtWidgetRid(handle));
    OS.PgSetDrawMode(Pg_DRAWMODE_XOR);
    OS.PgSetFillColor(color);
    int nWidth = width;
    if (nWidth <= 0) {
      nWidth = 2;
    }
    OS.PgDrawIRect(x, y, (x + nWidth) - 1, (y + height) - 1, Pg_DRAW_FILL);
    OS.PgSetGC(prevContext);
    OS.PgDestroyGC(phGC);
    return true;
  }
}
