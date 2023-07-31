class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    int parentHandle = parent.handle;
    createScrolledHandle(parentHandle);
  }
}
