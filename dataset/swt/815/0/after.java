class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int count = getItemCount();
    if (!((0 <= start) && (start < count))) {
      return -1;
    }
    for (int i = start; i < count; i++) {
      if (string.equals(getItem(i))) {
        return i;
      }
    }
    return -1;
  }
}
