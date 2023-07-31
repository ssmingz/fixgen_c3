class PlaceHold {
  void HandleKeyUp(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    sendKeyEvent(KeyUp, e, false);
  }
}
