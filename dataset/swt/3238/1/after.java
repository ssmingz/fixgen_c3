class Font {
  public Font(Device device, FontData fd) {
    super(device);
    if (fd == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(fd.getName(), fd.getHeight(), fd.getStyle(), fd.stem);
    init();
  }
}
