class Font {
  public Font(Device device, String name, int height, int style) {
    super(device);
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(name, height, style, null);
    init();
  }
}
