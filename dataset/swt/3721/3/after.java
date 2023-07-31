class PlaceHold {
  int insertText(int offset, long pbstrText) {
    if (DEBUG) {
      print(
          (((this + ".IAccessibleEditableText::insertText, offset=") + offset) + ", pbstrText=")
              + pbstrText);
    }
    AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
    event.start = (offset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : offset;
    event.end = event.start;
    event.string = getString(pbstrText);
    for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
      AccessibleEditableTextListener listener = accessibleEditableTextListeners.get(i);
      listener.replaceText(event);
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
