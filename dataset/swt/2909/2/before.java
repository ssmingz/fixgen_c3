class Color {
  public Color(Device device, RGB rgb) {
    if (rgb == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, rgb.red, rgb.green, rgb.blue);
  }
}
