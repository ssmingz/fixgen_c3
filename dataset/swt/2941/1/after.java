class PlaceHold {
  void HandlePreviewKeyUp(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    sendKeyEvent(KeyUp, e, false);
  }
}
