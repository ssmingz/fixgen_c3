class PlaceHold {
  void textViewDidChangeSelection(int aNotification) {
    NSNotification notification = new NSNotification(aNotification);
    NSText editor = new NSText(notification.object().id);
    selectionRange = editor.selectedRange();
  }
}
