class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    createScrolledHandle(handle);
  }
}
