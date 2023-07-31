class PlaceHold {
  void handleEvent(int evt) {
    int type = Cocoa.objc_msgSend(evt, S_type);
    int length = OS.CFStringGetLength(type);
    char[] buffer = new char[length];
    CFRange range = new CFRange();
    range.length = length;
    OS.CFStringGetCharacters(type, range, buffer);
    String typeString = new String(buffer);
    if (typeString.equals(DOMEVENT_FOCUSIN)) {
      hasNewFocusElement = true;
      return;
    }
    if (typeString.equals(DOMEVENT_FOCUSOUT)) {
      hasNewFocusElement = false;
      return;
    }
    boolean ctrl = Cocoa.objc_msgSend(evt, S_ctrlKey) != 0;
    boolean shift = Cocoa.objc_msgSend(evt, S_shiftKey) != 0;
    boolean alt = Cocoa.objc_msgSend(evt, S_altKey) != 0;
    boolean meta = Cocoa.objc_msgSend(evt, S_metaKey) != 0;
    if (DOMEVENT_KEYDOWN.equals(typeString) || DOMEVENT_KEYUP.equals(typeString)) {
      int keyCode = Cocoa.objc_msgSend(evt, S_keyCode);
      int charCode = Cocoa.objc_msgSend(evt, S_charCode);
      Event keyEvent = new Event();
      keyEvent.widget = browser;
      if (DOMEVENT_KEYDOWN.equals(typeString)) {
        keyEvent.type = SWT.KeyDown;
      } else {
        keyEvent.type = SWT.KeyUp;
      }
      keyEvent.keyCode = translateKey(keyCode);
      keyEvent.character = ((char) (charCode));
      keyEvent.stateMask =
          (((alt ? SWT.ALT : 0) | (ctrl ? SWT.CTRL : 0)) | (shift ? SWT.SHIFT : 0))
              | (meta ? SWT.COMMAND : 0);
      browser.notifyListeners(keyEvent.type, keyEvent);
      boolean doit = keyEvent.doit;
      if (((doit && (OS.VERSION < 0x1050)) && (keyEvent.keyCode == SWT.TAB))
          && ((keyEvent.stateMask & SWT.SHIFT) != 0)) {
        doit = false;
      }
      if (!doit) {
        Cocoa.objc_msgSend(evt, S_preventDefault);
      } else if (((!hasNewFocusElement) && (keyEvent.keyCode == SWT.TAB))
          && DOMEVENT_KEYUP.equals(typeString)) {
        browser.traverse(TRAVERSE_TAB_NEXT);
        hasNewFocusElement = false;
      }
      return;
    }
    int clientX = Cocoa.objc_msgSend(evt, S_clientX);
    int clientY = Cocoa.objc_msgSend(evt, S_clientY);
    int detail = Cocoa.objc_msgSend(evt, S_detail);
    Event mouseEvent = new Event();
    mouseEvent.widget = browser;
    mouseEvent.x = clientX;
    mouseEvent.y = clientY;
    mouseEvent.stateMask =
        (((alt ? SWT.ALT : 0) | (ctrl ? SWT.CTRL : 0)) | (shift ? SWT.SHIFT : 0))
            | (meta ? SWT.COMMAND : 0);
    if (DOMEVENT_MOUSEDOWN.equals(typeString)) {
      mouseEvent.type = SWT.MouseDown;
      int button = Cocoa.objc_msgSend(evt, S_button);
      mouseEvent.button = button + 1;
      mouseEvent.count = detail;
    } else if (DOMEVENT_MOUSEUP.equals(typeString)) {
      mouseEvent.type = SWT.MouseUp;
      int button = Cocoa.objc_msgSend(evt, S_button);
      mouseEvent.button = button + 1;
      mouseEvent.count = detail;
      switch (mouseEvent.button) {
        case 1:
          mouseEvent.stateMask |= SWT.BUTTON1;
          break;
        case 2:
          mouseEvent.stateMask |= SWT.BUTTON2;
          break;
        case 3:
          mouseEvent.stateMask |= SWT.BUTTON3;
          break;
        case 4:
          mouseEvent.stateMask |= SWT.BUTTON4;
          break;
        case 5:
          mouseEvent.stateMask |= SWT.BUTTON5;
          break;
      }
    } else if (DOMEVENT_MOUSEMOVE.equals(typeString)) {
      if ((mouseEvent.x == lastMouseMoveX) && (mouseEvent.y == lastMouseMoveY)) {
        return;
      }
      mouseEvent.type = SWT.MouseMove;
      lastMouseMoveX = mouseEvent.x;
      lastMouseMoveY = mouseEvent.y;
    } else if (DOMEVENT_MOUSEWHEEL.equals(typeString)) {
      mouseEvent.type = SWT.MouseWheel;
      int delta = Cocoa.objc_msgSend(evt, S_wheelDelta);
      mouseEvent.count = delta / 120;
    }
    browser.notifyListeners(mouseEvent.type, mouseEvent);
    if ((detail == 2) && DOMEVENT_MOUSEDOWN.equals(typeString)) {
      int button = Cocoa.objc_msgSend(evt, S_button);
      mouseEvent = new Event();
      mouseEvent.widget = browser;
      mouseEvent.x = clientX;
      mouseEvent.y = clientY;
      mouseEvent.stateMask =
          (((alt ? SWT.ALT : 0) | (ctrl ? SWT.CTRL : 0)) | (shift ? SWT.SHIFT : 0))
              | (meta ? SWT.COMMAND : 0);
      mouseEvent.type = SWT.MouseDoubleClick;
      mouseEvent.button = button + 1;
      mouseEvent.count = detail;
      browser.notifyListeners(mouseEvent.type, mouseEvent);
    }
  }
}
