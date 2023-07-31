class PlaceHold {
  public static Color getColorByName(String colorName) {
    colorName = colorName.toLowerCase(Locale.ENGLISH);
    if (colorName.equals(COLOR_BLACK)) {
      return Color.black;
    } else if (colorName.equals(COLOR_BLUE)) {
      return Color.blue;
    } else if (colorName.equals(COLOR_CYAN)) {
      return Color.cyan;
    } else if (colorName.equals(COLOR_DARKGRAY) || colorName.equals(COLOR_DARKGREY)) {
      return Color.darkGray;
    } else if (colorName.equals(COLOR_GRAY) || colorName.equals(COLOR_GREY)) {
      return Color.gray;
    } else if (colorName.equals(COLOR_LIGHTGRAY) || colorName.equals(COLOR_LIGHTGREY)) {
      return Color.lightGray;
    } else if (colorName.equals(COLOR_GREEN)) {
      return Color.green;
    } else if (colorName.equals(COLOR_MAGENTA)) {
      return Color.magenta;
    } else if (colorName.equals(COLOR_ORANGE)) {
      return Color.orange;
    } else if (colorName.equals(COLOR_PINK)) {
      return Color.pink;
    } else if (colorName.equals(COLOR_RED)) {
      return Color.red;
    } else if (colorName.equals(COLOR_WHITE)) {
      return Color.white;
    } else if (colorName.equals(COLOR_YELLOW)) {
      return Color.yellow;
    }
    return Color.black;
  }
}
