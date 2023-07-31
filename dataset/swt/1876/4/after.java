class Font {
  public Font(Device device, FontData fd) {
    if (fd == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, fd);
  }
}
