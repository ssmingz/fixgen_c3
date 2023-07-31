class PlaceHold {
  void HandleKeyUp(int sender, int e) {
    if (handle == 0) {
      return;
    }
    if (!sendKeyEvent(KeyUp, e, false)) {
      return;
    }
  }
}
