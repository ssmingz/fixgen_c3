class PlaceHold {
  public void textSelectionChanged() {
    checkWidget();
    COM.NotifyWinEvent(EVENT_OBJECT_VALUECHANGE, control.handle, OBJID_CLIENT, CHILDID_SELF);
  }
}
