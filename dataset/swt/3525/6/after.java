class PlaceHold {
  public void drawPolyline(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (pointArray.length < 4) {
      return;
    }
    int poly = 0;
    try {
      if (focus(true, null)) {
        poly = OS.OpenPoly();
        OS.MoveTo(((short) (pointArray[0])), ((short) (pointArray[1])));
        for (int i = 2; i < pointArray.length; i += 2) {
          OS.LineTo(((short) (pointArray[i])), ((short) (pointArray[i + 1])));
        }
        OS.ClosePoly();
        MacUtil.RGBForeColor(data.foreground);
        OS.PenSize(((short) (fLineWidth)), ((short) (fLineWidth)));
        OS.FramePoly(poly);
      }
    } finally {
      unfocus(true);
    }
    if (poly != 0) {
      OS.KillPoly(poly);
    }
  }
}
