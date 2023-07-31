class PlaceHold {
  static int checkIndex(int index) {
    if (index < 0) {
      SWT.error(ERROR_ITEM_NOT_ADDED);
    }
    return index;
  }
}
