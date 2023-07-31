class PlaceHold {
  static DropTarget FindDropTarget(int handle) {
    Display display = Display.findDisplay(Thread.currentThread());
    if ((display == null) || display.isDisposed()) {
      return null;
    }
    Widget widget = display.findWidget(handle);
    if (widget == null) {
      return null;
    }
    return ((DropTarget) (widget.getData(DROPTARGETID)));
  }
}
