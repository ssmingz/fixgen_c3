class PlaceHold {
  int mouseHoverProc(int handle, int id) {
    mouseHoverID = mouseHoverHandle = 0;
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.hoverProc(id);
  }
}
