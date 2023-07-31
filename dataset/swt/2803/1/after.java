class PlaceHold {
  boolean sendMouseWheel(short wheelAxis, int wheelDelta) {
    if ((state & CANVAS) != 0) {
      ScrollBar bar = (wheelAxis == OS.kEventMouseWheelAxisX) ? horizontalBar : verticalBar;
      if ((bar != null) && bar.getEnabled()) {
        bar.setSelection(Math.max(0, bar.getSelection() - (bar.getIncrement() * wheelDelta)));
        Event event = new Event();
        event.detail = (wheelDelta > 0) ? SWT.PAGE_UP : SWT.PAGE_DOWN;
        bar.sendSelectionEvent(Selection, event, true);
        return true;
      }
    }
    return false;
  }
}
