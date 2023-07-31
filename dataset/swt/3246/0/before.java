class PlaceHold {
  void HandleKeyUp(int sender, int e) {
    if (!sendKeyEvent(KeyUp, e, false)) {
      return;
    }
  }
}
