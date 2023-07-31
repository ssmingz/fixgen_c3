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
    dockImage = 0;
    if (runLoopObserver != 0) {
      OS.CFRunLoopObserverInvalidate(runLoopObserver);
      OS.CFRelease(runLoopObserver);
    }
    if (runLoopSource != 0) {
      OS.CFRunLoopSourceInvalidate(runLoopSource);
      OS.CFRelease(runLoopSource);
    }
    runLoop = runLoopSource = runLoopObserver = 0;
    releaseCallback.dispose();
    actionCallback.dispose();
    appleEventCallback.dispose();
    caretCallback.dispose();
    clockCallback.dispose();
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
    observerCallback.dispose();
    sourceCallback.dispose();
    searchCallback.dispose();
    coreEventCallback.dispose();
    pollingCallback.dispose();
    actionCallback =
        appleEventCallback = caretCallback = commandCallback = appearanceCallback = null;
    accessibilityCallback =
        clockCallback =
            controlCallback = drawItemCallback = itemDataCallback = itemNotificationCallback = null;
    helpCallback =
        hitTestCallback =
            keyboardCallback =
                menuCallback = itemCompareCallback = searchCallback = trayItemCallback = null;
    mouseHoverCallback =
        mouseCallback =
            trackingCallback =
                windowCallback = colorCallback = observerCallback = sourceCallback = null;
    textInputCallback = coreEventCallback = releaseCallback = pollingCallback = null;
    actionProc =
        appleEventProc = caretProc = commandProc = appearanceProc = searchProc = trayItemProc = 0;
    accessibilityProc =
        clockProc =
            controlProc = drawItemProc = itemDataProc = itemNotificationProc = itemCompareProc = 0;
    helpProc = hitTestProc = keyboardProc = menuProc = observerProc = sourceProc = releaseProc = 0;
    mouseHoverProc = mouseProc = trackingProc = windowProc = colorProc = coreEventProc = 0;
    textInputProc = pollingProc = 0;
    timerCallback.dispose();
    timerCallback = null;
    timerList = null;
    timerProc = 0;
    currentControl = focusControl = focusCombo = null;
    activeShell = null;
    helpWidget = null;
    if (helpString != 0) {
      OS.CFRelease(helpString);
    }
    helpString = 0;
    widgetTable = menus = popups = null;
    modalShells = null;
    menuBar = null;
    eventTable = filterTable = null;
    thread = null;
    indexTable = property = kchrState = null;
    buttonInset =
        tabFolderNorthInset =
            tabFolderSouthInset = comboInset = editTextInset = searchTextInset = null;
    data = highlightColor = null;
    values = keys = null;
  }
}
