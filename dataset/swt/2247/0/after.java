class PlaceHold {
  RECT getBounds(int row, int column, boolean getText, boolean getImage, boolean full) {
    return getBounds(row, column, getText, getImage, full, false, 0);
  }
}
