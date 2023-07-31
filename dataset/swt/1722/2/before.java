class PlaceHold {
  void removeMouseHoverTimeout(int handle) {
    if (handle != mouseHoverHandle) {
      return;
    }
    if (mouseHoverId != 0) {
      OS.gtk_timeout_remove(mouseHoverId);
    }
    mouseHoverId = 0;
    mouseHoverHandle = 0;
  }
}
