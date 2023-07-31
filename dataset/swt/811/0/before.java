class PlaceHold {
  boolean isMyHandle(int h) {
    if (h == topHandle) {
      return true;
    }
    if (h == vboxHandle) {
      return true;
    }
    if (h == eventBoxHandle) {
      return true;
    }
    if (h == fixedHandle) {
      return true;
    }
    if (h == handle) {
      return true;
    }
    return false;
  }
}
