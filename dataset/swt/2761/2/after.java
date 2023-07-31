class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    int pixel = OS.Pt_DEFAULT_COLOR;
    if (color != null) {
      if (color.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      pixel = color.handle;
    }
    int[] args = new int[] {OS.Pt_ARG_FILL_COLOR, pixel, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
