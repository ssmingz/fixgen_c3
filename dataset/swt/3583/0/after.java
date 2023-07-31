class PlaceHold {
  public void textCaretMoved(int index) {
    checkWidget();
    COM.NotifyWinEvent(EVENT_OBJECT_LOCATIONCHANGE, control.handle, OBJID_CARET, CHILDID_SELF);
    COM.NotifyWinEvent(EVENT_TEXT_CARET_MOVED, control.handle, OBJID_CLIENT, CHILDID_SELF);
  }
}
