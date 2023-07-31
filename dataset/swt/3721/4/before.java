class PlaceHold {
  int replaceText(int startOffset, int endOffset, long pbstrText) {
    if (DEBUG) {
      print(
          (((((this + ".IAccessibleEditableText::replaceText, start=") + startOffset) + ", end=")
                      + endOffset)
                  + ", pbstrText=")
              + pbstrText);
    }
    AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
    event.start = (startOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : startOffset;
    event.end = (endOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : endOffset;
    event.string = getString(pbstrText);
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
