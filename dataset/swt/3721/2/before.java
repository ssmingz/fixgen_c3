class PlaceHold {
  int pasteText(int offset) {
    if (DEBUG) {
      print((this + ".IAccessibleEditableText::pasteText, offset=") + offset);
    }
    AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
    event.start = (offset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : offset;
    event.end = event.start;
    for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
      AccessibleEditableTextListener listener =
          ((AccessibleEditableTextListener) (accessibleEditableTextListeners.elementAt(i)));
      listener.pasteText(event);
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
