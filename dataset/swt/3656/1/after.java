class PlaceHold {
  int processSelection(int info) {
    if (((style & SWT.CASCADE) != 0) && (menu != null)) {
      return OS.Pt_CONTINUE;
    }
    Event event = new Event();
    if (info != 0) {
      PtCallbackInfo_t cbinfo = new PtCallbackInfo_t();
      OS.memmove(cbinfo, info, sizeof);
      if (cbinfo.event != 0) {
        PhEvent_t ev = new PhEvent_t();
        OS.memmove(ev, cbinfo.event, sizeof);
        int data = OS.PhGetData(cbinfo.event);
        if (data != 0) {
          switch (ev.type) {
            case OS.Ph_EV_KEY:
              PhKeyEvent_t ke = new PhKeyEvent_t();
              OS.memmove(ke, data, sizeof);
              setKeyState(event, ke);
              break;
            case OS.Ph_EV_BUT_PRESS:
            case OS.Ph_EV_BUT_RELEASE:
              PhPointerEvent_t pe = new PhPointerEvent_t();
              OS.memmove(pe, data, sizeof);
              setMouseState(event, pe, ev);
              break;
          }
        }
      }
    }
    if ((style & SWT.RADIO) != 0) {
      if ((parent.getStyle() & SWT.NO_RADIO_GROUP) == 0) {
        selectRadio();
      }
    }
    postEvent(Selection, event);
    return OS.Pt_CONTINUE;
  }
}
