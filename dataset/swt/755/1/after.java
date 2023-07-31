class PlaceHold {
  boolean insertText(int id, int sel, int string) {
    if (startOffset == (-1)) {
      return true;
    }
    NSString str = new NSString(string);
    if (str.isKindOfClass(class_NSAttributedString)) {
      str = new NSAttributedString(string).string();
    }
    int length = ((int) (str.length()));
    int end = startOffset + text.length();
    resetStyles();
    caretOffset = commitCount = length;
    Event event = new Event();
    event.detail = SWT.COMPOSITION_CHANGED;
    event.start = startOffset;
    event.end = end;
    event.text = text = str.getString();
    sendEvent(ImeComposition, event);
    text = "";
    caretOffset = commitCount = 0;
    startOffset = -1;
    return event.doit;
  }
}
