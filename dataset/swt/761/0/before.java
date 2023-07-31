class PlaceHold {
  int processMouseDown(int callData) {
    Display display = getDisplay();
    display.hideToolTip();
    XButtonEvent xEvent = new XButtonEvent();
    OS.memmove(xEvent, callData, sizeof);
    sendMouseEvent(MouseDown, xEvent.button, xEvent);
    if ((xEvent.button == 2) && hooks(DragDetect)) {
      sendEvent(DragDetect);
    }
    if ((xEvent.button == 3) && (menu != null)) {
      OS.XmProcessTraversal(handle, XmTRAVERSE_CURRENT);
      menu.setVisible(true);
    }
    int clickTime = display.getDoubleClickTime();
    int lastTime = display.lastTime;
    int eventTime = xEvent.time;
    int lastButton = display.lastButton;
    int eventButton = xEvent.button;
    if (((lastButton == eventButton) && (lastTime != 0))
        && (Math.abs(lastTime - eventTime) <= clickTime)) {
      sendMouseEvent(MouseDoubleClick, eventButton, xEvent);
    }
    display.lastTime = (eventTime == 0) ? 1 : eventTime;
    display.lastButton = eventButton;
    return 0;
  }
}
