class PlaceHold {
  void init(int red, int green, int blue) {
    if ((((((red > 255) || (red < 0)) || (green > 255)) || (green < 0)) || (blue > 255))
        || (blue < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    double[] rgbColor = new double[4];
    rgbColor[0] = red / 255.0F;
    rgbColor[1] = green / 255.0F;
    rgbColor[2] = blue / 255.0F;
    rgbColor[3] = 1;
    handle = rgbColor;
  }
}
