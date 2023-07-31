class PlaceHold {
  boolean isMyHandle(int h) {
    if (h == fixedHandle) {
      return true;
    }
    if (h == scrolledHandle) {
      return true;
    }
    if (h == handle) {
      return true;
    }
    return false;
  }
}
