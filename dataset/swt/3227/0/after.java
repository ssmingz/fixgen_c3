class PlaceHold {
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof Color)) {
      return false;
    }
    Color color = ((Color) (object));
    double[] rgbColor = color.handle;
    if (handle == rgbColor) {
      return true;
    }
    return (((device == color.device) && (((int) (handle[0] * 255)) == ((int) (rgbColor[0] * 255))))
            && (((int) (handle[1] * 255)) == ((int) (rgbColor[1] * 255))))
        && (((int) (handle[2] * 255)) == ((int) (rgbColor[2] * 255)));
  }
}
