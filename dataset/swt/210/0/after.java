class PlaceHold {
  public int indexOf(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int itemCount = _getItemCount();
    for (int i = 0; i < itemCount; i++) {
      String s = _getItem(i);
      if ((s != null) && string.equals(s)) {
        return i;
      }
    }
    return -1;
  }
}
