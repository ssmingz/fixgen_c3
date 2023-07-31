class PlaceHold {
  int get_text(int startOffset, int endOffset, long pbstrText) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.start = (startOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : startOffset;
    event.end = (endOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : endOffset;
    if (event.start > event.end) {
      int temp = event.start;
      event.start = event.end;
      event.end = temp;
    }
    event.count = 0;
    event.type = ACC.TEXT_BOUNDARY_ALL;
    for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
      AccessibleTextExtendedListener listener = accessibleTextExtendedListeners.get(i);
      listener.getText(event);
    }
    if (event.result == null) {
      AccessibleControlEvent e = new AccessibleControlEvent(this);
      e.childID = ACC.CHILDID_SELF;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener = accessibleControlListeners.get(i);
        listener.getRole(e);
        listener.getValue(e);
      }
      if (e.detail == ACC.ROLE_TEXT) {
        event.result = e.result;
      }
    }
    if (DEBUG) {
      print(
          ((((((this + ".IAccessibleText::get_text(") + startOffset) + ", ") + endOffset)
                      + ") returning ")
                  + event.result)
              + hresult(event.result == null ? COM.E_INVALIDARG : COM.S_OK));
    }
    setString(pbstrText, event.result);
    if (event.result == null) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
