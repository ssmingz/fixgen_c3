class PlaceHold {
  boolean isMyHandle(int h) {
    if (h == eventBoxHandle) {
      return true;
    }
    if (h == notebookHandle) {
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
