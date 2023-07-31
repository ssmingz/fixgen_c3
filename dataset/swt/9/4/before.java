class PlaceHold {
  int processKey(int info) {
    if (!hasFocus()) {
      return OS.Pt_PROCESS;
    }
    if (info == 0) {
      return OS.Pt_PROCESS;
    }
    PtCallbackInfo_t cbinfo = new PtCallbackInfo_t();
    OS.memmove(cbinfo, info, sizeof);
    if (cbinfo.event == 0) {
      return OS.Pt_PROCESS;
    }
    PhEvent_t ev = new PhEvent_t();
    OS.memmove(ev, cbinfo.event, sizeof);
    if ((ev.processing_flags & OS.Ph_FAKE_EVENT) != 0) {
      return OS.Pt_PROCESS;
    }
    int data = OS.PhGetData(cbinfo.event);
    if (data == 0) {
      return OS.Pt_PROCESS;
    }
    PhKeyEvent_t ke = new PhKeyEvent_t();
    OS.memmove(ke, data, sizeof);
    if (ke.key_flags == OS.Pk_KF_Scan_Valid) {
      return OS.Pt_PROCESS;
    }
    if ((ke.key_flags & OS.Pk_KF_Key_Repeat) != 0) {
      if ((ke.key_flags & OS.Pk_KF_Sym_Valid) != 0) {
        switch (ke.key_sym) {
          case OS.Pk_Alt_L:
          case OS.Pk_Alt_R:
          case OS.Pk_Control_L:
          case OS.Pk_Control_R:
          case OS.Pk_Shift_L:
          case OS.Pk_Shift_R:
            return OS.Pt_PROCESS;
        }
      }
    }
    if ((ke.key_flags & (OS.Pk_KF_Key_Down | OS.Pk_KF_Key_Repeat)) != 0) {
      int key = ke.key_sym;
      if ((ke.key_flags & OS.Pk_KF_Sym_Valid) == 0) {
        key = 0;
        if ((ke.key_flags & OS.Pk_KF_Cap_Valid) != 0) {
          if ((ke.key_cap == OS.Pk_Tab) && ((ke.key_mods & OS.Pk_KM_Ctrl) != 0)) {
            key = OS.Pk_Tab;
          }
        }
      }
      switch (key) {
        case OS.Pk_Escape:
        case OS.Pk_Return:
        case OS.Pk_KP_Tab:
        case OS.Pk_Tab:
        case OS.Pk_Up:
        case OS.Pk_Down:
        case OS.Pk_Left:
        case OS.Pk_Right:
        case OS.Pk_Pg_Up:
        case OS.Pk_Pg_Down:
          {
            if (key != OS.Pk_Return) {
              ev.processing_flags |= OS.Ph_NOT_CUAKEY;
              OS.memmove(cbinfo.event, ev, sizeof);
            }
            if (translateTraversal(key, ke)) {
              ev.processing_flags |= OS.Ph_CONSUMED;
              OS.memmove(cbinfo.event, ev, sizeof);
              return OS.Pt_PROCESS;
            }
          }
      }
    }
    Display display = getDisplay();
    Event event = new Event();
    event.time = ev.timestamp;
    int type = SWT.KeyUp;
    if ((ke.key_flags & (OS.Pk_KF_Key_Down | OS.Pk_KF_Key_Repeat)) != 0) {
      type = SWT.KeyDown;
    }
    if ((ke.key_flags & OS.Pk_KF_Sym_Valid) != 0) {
      event.keyCode = Display.translateKey(ke.key_sym);
      if (event.keyCode == 0) {
        switch (ke.key_sym) {
          case OS.Pk_BackSpace:
            event.character = '\b';
            break;
          case OS.Pk_KP_Tab:
          case OS.Pk_Tab:
            event.character = '\t';
            break;
          case OS.Pk_Linefeed:
            event.character = '\n';
            break;
          case OS.Pk_Clear:
            event.character = 0xb;
            break;
          case OS.Pk_Return:
            event.character = '\r';
            break;
          case OS.Pk_Pause:
            event.character = 0x13;
            break;
          case OS.Pk_Scroll_Lock:
            event.character = 0x14;
            break;
          case OS.Pk_Escape:
            event.character = 0x1b;
            break;
          case OS.Pk_Delete:
            event.character = 0x7f;
            break;
          default:
            event.character = ((char) (ke.key_sym));
        }
      }
      display.lastKey = event.keyCode;
      display.lastAscii = event.character;
    }
    setKeyState(event, ke);
    if (type == SWT.KeyUp) {
      if (event.keyCode == 0) {
        event.keyCode = display.lastKey;
      }
      if (event.character == 0) {
        event.character = ((char) (display.lastAscii));
      }
    }
    postEvent(type, event);
    return OS.Pt_PROCESS;
  }
}
