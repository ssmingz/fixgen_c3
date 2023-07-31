class PlaceHold {
  int findBrush(int value, int lbStyle) {
    if (lbStyle == OS.BS_SOLID) {
      for (int i = 0; i < Shell.SYSTEM_COLORS.length; i++) {
        if (value == OS.GetSysColor(SYSTEM_COLORS[i])) {
          return OS.GetSysColorBrush(SYSTEM_COLORS[i]);
        }
      }
    }
    if (brushes == null) {
      brushes = new int[BRUSHES_SIZE];
    }
    LOGBRUSH logBrush = new LOGBRUSH();
    for (int i = 0; i < brushes.length; i++) {
      int hBrush = brushes[i];
      if (hBrush == 0) {
        break;
      }
      OS.GetObject(hBrush, sizeof, logBrush);
      switch (logBrush.lbStyle) {
        case OS.BS_SOLID:
          if (lbStyle == OS.BS_SOLID) {
            if (logBrush.lbColor == value) {
              return hBrush;
            }
          }
          break;
        case OS.BS_PATTERN:
          if (lbStyle == OS.BS_PATTERN) {
            if (logBrush.lbHatch == value) {
              return hBrush;
            }
          }
          break;
      }
    }
    int length = brushes.length;
    int hBrush = brushes[--length];
    if (hBrush != 0) {
      OS.DeleteObject(hBrush);
    }
    System.arraycopy(brushes, 0, brushes, 1, length);
    switch (lbStyle) {
      case OS.BS_SOLID:
        hBrush = OS.CreateSolidBrush(((int) (value)));
        break;
      case OS.BS_PATTERN:
        hBrush = OS.CreatePatternBrush(value);
        break;
    }
    return brushes[0] = hBrush;
  }
}
