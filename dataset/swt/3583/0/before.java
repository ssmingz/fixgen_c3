class PlaceHold {
  public void textCaretMoved(int index) {
    checkWidget();
    COM.NotifyWinEvent(EVENT_OBJECT_LOCATIONCHANGE, control.handle, OBJID_CARET, CHILDID_SELF);
  }
}
