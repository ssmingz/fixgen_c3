class PlaceHold {
  int deleteText(int startOffset, int endOffset) {
    if (DEBUG) {
      print(
          (((this + ".IAccessibleEditableText::deleteText, start=") + startOffset) + ", end=")
              + endOffset);
    }
    AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
    event.start = (startOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : startOffset;
    event.end = (endOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : endOffset;
    event.string = "";
    for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
      AccessibleEditableTextListener listener =
          ((AccessibleEditableTextListener) (accessibleEditableTextListeners.elementAt(i)));
      listener.replaceText(event);
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
