class Font {
  public Font(Device device, FontData fd) {
    super(device);
    if (fd == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(new FontData[] {fd});
    init();
  }
}
