class PlaceHold {
  void sendSearchSelection() {
    if (targetSearch != null) {
      ((NSSearchField) (view)).sendAction(actionSearch, targetSearch);
    }
    Event event = new Event();
    event.detail = SWT.ICON_SEARCH;
    sendSelectionEvent(DefaultSelection, event, false);
  }
}
