class PlaceHold {
  public void drawLine(int x1, int y1, int x2, int y2) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    try {
      if (focus(true, null)) {
        installForeColor(data.foreground);
        OS.PenSize(((short) (fLineWidth)), ((short) (fLineWidth)));
        OS.MoveTo(((short) (x1)), ((short) (y1)));
        OS.LineTo(((short) (x2)), ((short) (y2)));
      }
    } finally {
      unfocus(true);
    }
  }
}
