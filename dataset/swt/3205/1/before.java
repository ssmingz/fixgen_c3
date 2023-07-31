class PlaceHold {
  void releaseDisplay() {
    disposeWindows();
    if (gcWindow != 0) {
      OS.DisposeWindow(gcWindow);
    }
    gcWindow = 0;
    if (caretID != 0) {
      OS.RemoveEventLoopTimer(caretID);
    }
    if (mouseHoverID != 0) {
      OS.RemoveEventLoopTimer(mouseHoverID);
    }
    caretID = mouseHoverID = 0;
    if (timerIds != null) {
      for (int i = 0; i < timerIds.length; i++) {
        if ((timerIds[i] != 0) && (timerIds[i] != (-1))) {
          OS.RemoveEventLoopTimer(timerIds[i]);
        }
      }
    }
    timerIds = null;
    actionCallback.dispose();
    appleEventCallback.dispose();
    caretCallback.dispose();
    commandCallback.dispose();
    controlCallback.dispose();
    accessibilityCallback.dispose();
    drawItemCallback.dispose();
    itemCompareCallback.dispose();
    itemDataCallback.dispose();
    itemNotificationCallback.dispose();
    helpCallback.dispose();
    hitTestCallback.dispose();
    keyboardCallback.dispose();
    menuCallback.dispose();
    mouseHoverCallback.dispose();
    mouseCallback.dispose();
    trackingCallback.dispose();
    windowCallback.dispose();
    colorCallback.dispose();
    textInputCallback.dispose();
    appearanceCallback.dispose();
    trayItemCallback.dispose();
    pollingCallback.dispose();
    actionCallback =
        appleEventCallback = caretCallback = commandCallback = appearanceCallback = null;
    accessibilityCallback =
        controlCallback = drawItemCallback = itemDataCallback = itemNotificationCallback = null;
    helpCallback =
        hitTestCallback =
            keyboardCallback = menuCallback = itemCompareCallback = trayItemCallback = null;
    mouseHoverCallback =
        mouseCallback = trackingCallback = windowCallback = colorCallback = pollingCallback = null;
    textInputCallback = null;
    actionProc = appleEventProc = caretProc = commandProc = appearanceProc = trayItemProc = 0;
    accessibilityProc =
        controlProc = drawItemProc = itemDataProc = itemNotificationProc = itemCompareProc = 0;
    helpProc = hitTestProc = keyboardProc = menuProc = pollingProc = 0;
    mouseHoverProc = mouseProc = trackingProc = windowProc = colorProc = 0;
    textInputProc = 0;
    timerCallback.dispose();
    timerCallback = null;
    timerProc = 0;
    grabControl = currentControl = mouseUpControl = focusControl = focusCombo = null;
    helpWidget = null;
    if (helpString != 0) {
      OS.CFRelease(helpString);
    }
    helpString = 0;
    menus = popups = null;
    menuBar = null;
    if (errorImage != null) {
      errorImage.dispose();
    }
    if (infoImage != null) {
      infoImage.dispose();
    }
    if (warningImage != null) {
      warningImage.dispose();
    }
    errorImage = infoImage = warningImage = null;
    for (int i = 0; i < cursors.length; i++) {
      if (cursors[i] != null) {
        cursors[i].dispose();
      }
    }
    cursors = null;
    if (dockImage != 0) {
      OS.CGImageRelease(dockImage);
    }
    if (dockImageData != 0) {
      OS.DisposePtr(dockImageData);
    }
    dockImage = dockImageData = 0;
  }
}
