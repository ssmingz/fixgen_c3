class ScrollBar {
  ScrollBar(Scrollable parent, int style, int handle) {
    super(parent, checkStyle(style));
    this.parent = parent;
    this.handle = handle;
    state |= HANDLE;
    createWidget(0);
  }
}
