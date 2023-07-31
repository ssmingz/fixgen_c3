class PlaceHold {
  void setChecked(boolean checked, boolean notify) {
    this.checked = checked;
    cached = true;
    redraw(CHECK_COLUMN_ID);
    if (notify) {
      Event event = new Event();
      event.item = this;
      event.detail = SWT.CHECK;
      parent.sendSelectionEvent(Selection, event, false);
    }
  }
}
