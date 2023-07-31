class PlaceHold {
  public static Color gtk_new(Device device, GdkColor gdkColor) {
    Color color = new Color(device);
    color.handle = gdkColor;
    return color;
  }
}
