class PlaceHold {
  public void clear(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if (!(((0 <= start) && (start <= end)) && (end < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if ((start == 0) && (end == (itemCount - 1))) {
      clearAll();
    } else {
      for (int i = start; i <= end; i++) {
        clear(i);
      }
    }
  }
}
