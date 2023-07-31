class PlaceHold {
  public static GC win32_new(int hDC, GCData data) {
    GC gc = new GC();
    gc.device = data.device;
    data.style |= SWT.LEFT_TO_RIGHT;
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
      int flags = OS.GetLayout(hDC);
      if ((flags & OS.LAYOUT_RTL) != 0) {
        data.style |= SWT.RIGHT_TO_LEFT | SWT.MIRRORED;
      }
    }
    gc.init(null, data, hDC);
    return gc;
  }
}
