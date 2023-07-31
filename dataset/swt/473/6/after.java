class PlaceHold {
  public int indexOf(CoolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int answer = 0;
    for (int i = 0; i < rows.size(); i++) {
      Vector row = ((Vector) (rows.elementAt(i)));
      for (int j = 0; j < row.size(); j++) {
        CoolItem next = ((CoolItem) (row.elementAt(j)));
        if (next.equals(item)) {
          return answer;
        } else {
          answer++;
        }
      }
    }
    return -1;
  }
}
