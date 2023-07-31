class Font {
  public Font(Device device, String name, int height, int style) {
    super(device);
    if (name == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(new FontData(name, height, style));
    init();
  }
}
