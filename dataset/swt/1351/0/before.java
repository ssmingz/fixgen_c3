class PlaceHold {
  public static Color win32_new(Device device, int handle) {
    Color color = new Color(device);
    color.handle = handle;
    return color;
  }
}
