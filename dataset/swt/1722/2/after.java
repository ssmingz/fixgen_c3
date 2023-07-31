class PlaceHold {
  void removeMouseHoverTimeout(int handle) {
    if (handle != mouseHoverHandle) {
      return;
    }
    if (mouseHoverId != 0) {
      OS.g_source_remove(mouseHoverId);
    }
    mouseHoverId = 0;
    mouseHoverHandle = 0;
  }
}
