class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int itemCount = _getItemCount();
    if (!((0 <= start) && (start < itemCount))) {
      return -1;
    }
    for (int i = start; i < itemCount; i++) {
      String s = _getItem(i);
      if (string.equals(s)) {
        return i;
      }
    }
    return -1;
  }
}
