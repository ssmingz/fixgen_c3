class PlaceHold {
  int getTextPaintWidth(int columnIndex) {
    int result = internalGetTextWidth(columnIndex);
    if (result > 0) {
      result += 2 * MARGIN_TEXT;
    }
    return result;
  }
}
