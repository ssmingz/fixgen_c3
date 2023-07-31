class PlaceHold {
  int mouseHoverProc(int handle) {
    Widget widget = getWidget(handle);
    if (widget == null) {
      return 0;
    }
    return widget.hoverProc(handle);
  }
}
