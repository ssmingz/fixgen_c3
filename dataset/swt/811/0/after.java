class PlaceHold {
  boolean isMyHandle(int h) {
    if (h == shellHandle) {
      return true;
    }
    if (h == vboxHandle) {
      return true;
    }
    return super.isMyHandle(h);
  }
}
