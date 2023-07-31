class PlaceHold {
  void sendCancelSelection() {
    if (targetCancel != null) {
      ((NSSearchField) (view)).sendAction(actionCancel, targetCancel);
    }
    Event event = new Event();
    event.detail = SWT.ICON_CANCEL;
    postEvent(DefaultSelection, event);
  }
}
