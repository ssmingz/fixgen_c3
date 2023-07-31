class PlaceHold {
  boolean sendMouseEvent(NSEvent nsEvent, int type, boolean send) {
    NSInputManager manager = NSInputManager.currentInputManager();
    if ((manager != null) && manager.wantsToHandleMouseEvents()) {
      if (manager.handleMouseEvent(nsEvent)) {
        return true;
      }
    }
    Shell shell = null;
    Event event = new Event();
    switch (type) {
      case SWT.MouseDown:
        shell = getShell();
      case SWT.MouseUp:
      case SWT.MouseDoubleClick:
        int button = ((int) (nsEvent.buttonNumber()));
        switch (button) {
          case 0:
            event.button = 1;
            break;
          case 1:
            event.button = 3;
            break;
          case 2:
            event.button = 2;
            break;
          case 3:
            event.button = 4;
            break;
          case 4:
            event.button = 5;
            break;
        }
        break;
      case SWT.MouseWheel:
        event.detail = SWT.SCROLL_LINE;
        float delta = nsEvent.deltaY();
        event.count = (delta > 0) ? Math.max(1, ((int) (delta))) : Math.min(-1, ((int) (delta)));
        break;
    }
    if (event.button != 0) {
      event.count = ((int) (nsEvent.clickCount()));
    }
    NSPoint windowPoint = view.window().convertScreenToBase(NSEvent.mouseLocation());
    NSPoint point = view.convertPoint_fromView_(windowPoint, null);
    event.x = ((int) (point.x));
    event.y = ((int) (point.y));
    setInputState(event, nsEvent, type);
    if (send) {
      sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
    } else {
      postEvent(type, event);
    }
    if (shell != null) {
      shell.setActiveControl(this);
    }
    return event.doit;
  }
}
