class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (isDisposed()) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
    int window = OS.FrontWindow();
    int port = OS.GetWindowPort(window);
    int[] buffer = new int[1];
    OS.CreateCGContextForPort(port, buffer);
    int context = buffer[0];
    if (context == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      data.device = this;
      data.background = new float[] {0, 0, 0, 1};
      data.foreground = new float[] {1, 1, 1, 1};
      data.font = getSystemFont();
    }
    return context;
  }
}
