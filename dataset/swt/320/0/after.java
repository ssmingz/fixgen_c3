class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if (!(((0 <= start) && (start <= end)) && (end < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    int length = (end - start) + 1;
    int[] indices = new int[length];
    for (int i = 0; i < length; i++) {
      indices[i] = i + start;
    }
    remove(indices);
  }
}
