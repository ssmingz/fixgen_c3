class PlaceHold {
  int copyText(int startOffset, int endOffset) {
    if (DEBUG) {
      print(
          (((this + ".IAccessibleEditableText::copyText, start=") + startOffset) + ", end=")
              + endOffset);
    }
    AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
    event.start = (startOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : startOffset;
    event.end = (endOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : endOffset;
    for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
      AccessibleEditableTextListener listener =
          ((AccessibleEditableTextListener) (accessibleEditableTextListeners.elementAt(i)));
      listener.copyText(event);
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
