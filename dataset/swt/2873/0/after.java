class PlaceHold {
  void _addListener(int eventType, Listener listener) {
    super._addListener(eventType, listener);
    switch (eventType) {
      case SWT.MeasureItem:
      case SWT.EraseItem:
      case SWT.PaintItem:
        setCustomDraw(true);
        style |= SWT.DOUBLE_BUFFERED;
        setBackgroundTransparent(true);
        OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, LVS_EX_LABELTIP, 0);
        break;
    }
  }
}
