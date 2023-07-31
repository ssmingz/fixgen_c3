class PlaceHold {
  static int checkStyle(int style) {
    return checkBits(style, HORIZONTAL, VERTICAL, 0, 0, 0, 0);
  }
}
