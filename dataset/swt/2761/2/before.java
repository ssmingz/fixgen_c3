class PlaceHold {
  public void setBackground(Color color) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int pixel = OS.Pt_DEFAULT_COLOR;
    if (color != null) {
      pixel = color.handle;
    }
    int[] args = new int[] {OS.Pt_ARG_FILL_COLOR, pixel, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
