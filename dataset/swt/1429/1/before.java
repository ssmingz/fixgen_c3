class PlaceHold {
  public void clearAll() {
    checkWidget();
    for (int i = 0; i < itemCount; i++) {
      clear(i);
    }
  }
}
