class PlaceHold {
  public void setTextLimit(int limit) {
    checkWidget();
    if (limit == 0) {
      error(ERROR_CANNOT_BE_ZERO);
    }
    fTextLimit = limit;
  }
}
