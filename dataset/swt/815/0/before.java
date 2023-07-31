class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int count = getItemCount();
    for (int i = start; i < count; i++) {
      if (string.equals(getItem(i))) {
        return i;
      }
    }
    return -1;
  }
}
