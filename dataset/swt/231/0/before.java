class PlaceHold {
  void unsubclass() {
    super.unsubclass();
    if (ToolTipProc != 0) {
      if (toolTipHandle != 0) {
        OS.SetWindowLongPtr(toolTipHandle, GWLP_WNDPROC, ToolTipProc);
      }
      if (toolTipHandle != 0) {
        OS.SetWindowLongPtr(toolTipHandle, GWLP_WNDPROC, ToolTipProc);
      }
    }
  }
}
