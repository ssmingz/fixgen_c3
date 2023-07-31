class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    for (int i = start; i < items.length; i++) {
      if (string.equals(items[i])) {
        return i;
      }
    }
    return -1;
  }
}
