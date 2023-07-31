class PlaceHold {
  void setChecked(boolean checked, boolean notify) {
    this.checked = checked;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (notify) {
      Event event = new Event();
      event.item = this;
      event.detail = SWT.CHECK;
      parent.sendSelectionEvent(Selection, event, false);
    }
    redraw();
  }
}
