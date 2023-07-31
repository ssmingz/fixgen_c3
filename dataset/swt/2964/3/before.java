class Color {
  public Color(Device device, RGB rgb) {
    super(device);
    if (rgb == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(rgb.red, rgb.green, rgb.blue);
    init();
  }
}
