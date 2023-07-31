class PlaceHold {
  void initializeCallbacks() {
    actionCallback = new Callback(this, "actionProc", 2);
    actionProc = actionCallback.getAddress();
    if (actionProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    appleEventCallback = new Callback(this, "appleEventProc", 3);
    appleEventProc = appleEventCallback.getAddress();
    if (appleEventProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    caretCallback = new Callback(this, "caretProc", 2);
    caretProc = caretCallback.getAddress();
    if (caretProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    commandCallback = new Callback(this, "commandProc", 3);
    commandProc = commandCallback.getAddress();
    if (commandProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    controlCallback = new Callback(this, "controlProc", 3);
    controlProc = controlCallback.getAddress();
    if (controlProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    drawItemCallback = new Callback(this, "drawItemProc", 7);
    drawItemProc = drawItemCallback.getAddress();
    if (drawItemProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    itemDataCallback = new Callback(this, "itemDataProc", 5);
    itemDataProc = itemDataCallback.getAddress();
    if (itemDataProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    itemNotificationCallback = new Callback(this, "itemNotificationProc", 3);
    itemNotificationProc = itemNotificationCallback.getAddress();
    if (itemNotificationProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    helpCallback = new Callback(this, "helpProc", 5);
    helpProc = helpCallback.getAddress();
    if (helpProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    hitTestCallback = new Callback(this, "hitTestProc", 5);
    hitTestProc = hitTestCallback.getAddress();
    if (hitTestProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    keyboardCallback = new Callback(this, "keyboardProc", 3);
    keyboardProc = keyboardCallback.getAddress();
    if (keyboardProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    menuCallback = new Callback(this, "menuProc", 3);
    menuProc = menuCallback.getAddress();
    if (menuProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    mouseHoverCallback = new Callback(this, "mouseHoverProc", 2);
    mouseHoverProc = mouseHoverCallback.getAddress();
    if (mouseHoverProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    mouseCallback = new Callback(this, "mouseProc", 3);
    mouseProc = mouseCallback.getAddress();
    if (mouseProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    timerCallback = new Callback(this, "timerProc", 2);
    timerProc = timerCallback.getAddress();
    if (timerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    trackingCallback = new Callback(this, "trackingProc", 6);
    trackingProc = trackingCallback.getAddress();
    if (trackingProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback = new Callback(this, "windowProc", 3);
    windowProc = windowCallback.getAddress();
    if (windowProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    int[] mask1 = new int[] {OS.kEventClassCommand, OS.kEventProcessCommand};
    int appTarget = OS.GetApplicationEventTarget();
    OS.InstallEventHandler(appTarget, commandProc, mask1.length / 2, mask1, 0, null);
    int[] mask2 =
        new int[] {
          OS.kEventClassMouse,
          OS.kEventMouseDown,
          OS.kEventClassMouse,
          OS.kEventMouseDragged,
          OS.kEventClassMouse,
          OS.kEventMouseMoved,
          OS.kEventClassMouse,
          OS.kEventMouseUp,
          OS.kEventClassMouse,
          OS.kEventMouseWheelMoved
        };
    OS.InstallEventHandler(appTarget, mouseProc, mask2.length / 2, mask2, 0, null);
    int[] mask3 = new int[] {OS.kEventClassAppleEvent, OS.kEventAppleEvent};
    OS.InstallEventHandler(appTarget, appleEventProc, mask3.length / 2, mask3, 0, null);
    int[] mask4 =
        new int[] {
          OS.kEventClassKeyboard,
          OS.kEventRawKeyDown,
          OS.kEventClassKeyboard,
          OS.kEventRawKeyModifiersChanged,
          OS.kEventClassKeyboard,
          OS.kEventRawKeyRepeat,
          OS.kEventClassKeyboard,
          OS.kEventRawKeyUp
        };
    int focusTarget = OS.GetUserFocusEventTarget();
    OS.InstallEventHandler(focusTarget, keyboardProc, mask4.length / 2, mask4, 0, null);
  }
}
