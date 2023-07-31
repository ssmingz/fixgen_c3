class PlaceHold {
  static int checkIndex(int index) {
    if (index < 0) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    return index;
  }
}
