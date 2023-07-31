class PlaceHold {
  public void create(Composite parent, int style) {
    if (OS.VERSION < 0x1030) {
      browser.dispose();
      SWT.error(ERROR_NO_HANDLES);
    }
    int outControl[] = new int[1];
    try {
      Cocoa.HIWebViewCreate(outControl);
    } catch (UnsatisfiedLinkError e) {
      browser.dispose();
      SWT.error(ERROR_NO_HANDLES);
    }
    webViewHandle = outControl[0];
    if (webViewHandle == 0) {
      browser.dispose();
      SWT.error(ERROR_NO_HANDLES);
    }
    Display display = browser.getDisplay();
    display.setData(ADD_WIDGET_KEY, new Object[] {new Integer(webViewHandle), browser});
    if (!(OS.VERSION < 0x1040)) {
      browser.setData(SAFARI_EVENTS_FIX_KEY);
    }
    if (display.getData(BROWSER_WINDOW) == null) {
      Rect bounds = new Rect();
      OS.SetRect(bounds, ((short) (0)), ((short) (0)), ((short) (1)), ((short) (1)));
      final int[] outWindow = new int[1];
      OS.CreateNewWindow(kOverlayWindowClass, 0, bounds, outWindow);
      OS.ShowWindow(outWindow[0]);
      display.disposeExec(
          new Runnable() {
            public void run() {
              if (outWindow[0] != 0) {
                OS.DisposeWindow(outWindow[0]);
              }
              outWindow[0] = 0;
            }
          });
      display.setData(BROWSER_WINDOW, outWindow);
    }
    int window = OS.GetControlOwner(handle);
    int[] contentView = new int[1];
    OS.HIViewFindByID(OS.HIViewGetRoot(window), OS.kHIViewWindowContentID(), contentView);
    OS.HIViewAddSubview(contentView[0], webViewHandle);
    OS.HIViewChangeFeatures(webViewHandle, kHIViewFeatureIsOpaque, 0);
    OS.HIViewSetVisible(webViewHandle, true);
    if (browser.getShell().isVisible()) {
      int[] showEvent = new int[1];
      OS.CreateEvent(
          0, kEventClassWindow, kEventWindowShown, 0.0, kEventAttributeUserEvent, showEvent);
      OS.SetEventParameter(
          showEvent[0],
          kEventParamDirectObject,
          typeWindowRef,
          4,
          new int[] {OS.GetControlOwner(handle)});
      OS.SendEventToEventTarget(showEvent[0], OS.GetWindowEventTarget(window));
      if (showEvent[0] != 0) {
        OS.ReleaseEvent(showEvent[0]);
      }
    }
    final int webView = Cocoa.HIWebViewGetWebView(webViewHandle);
    final int notificationCenter = Cocoa.objc_msgSend(C_NSNotificationCenter, S_defaultCenter);
    Listener listener =
        new Listener() {
          public void handleEvent(Event e) {
            switch (e.type) {
              case SWT.Dispose:
                {
                  if (ignoreDispose) {
                    ignoreDispose = false;
                    break;
                  }
                  ignoreDispose = true;
                  browser.notifyListeners(e.type, e);
                  e.type = SWT.NONE;
                  OS.RemoveEventHandler(windowBoundsHandler);
                  windowBoundsHandler = 0;
                  e.display.setData(
                      ADD_WIDGET_KEY, new Object[] {new Integer(webViewHandle), null});
                  Cocoa.objc_msgSend(webView, S_setFrameLoadDelegate, 0);
                  Cocoa.objc_msgSend(webView, S_setResourceLoadDelegate, 0);
                  Cocoa.objc_msgSend(webView, S_setUIDelegate, 0);
                  Cocoa.objc_msgSend(webView, S_setPolicyDelegate, 0);
                  Cocoa.objc_msgSend(notificationCenter, S_removeObserver, delegate);
                  Cocoa.objc_msgSend(delegate, S_release);
                  OS.DisposeControl(webViewHandle);
                  html = null;
                  lastHoveredLinkURL = null;
                  break;
                }
            }
          }
        };
    browser.addListener(Dispose, listener);
    if (Callback3 == null) {
      Callback3 = new Callback(this.getClass(), "eventProc3", 3);
    }
    int callback3Address = Callback3.getAddress();
    if (callback3Address == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    int[] mask =
        new int[] {
          OS.kEventClassKeyboard,
          OS.kEventRawKeyDown,
          OS.kEventClassControl,
          OS.kEventControlDraw,
          OS.kEventClassControl,
          OS.kEventControlSetCursor,
          OS.kEventClassTextInput,
          OS.kEventTextInputUnicodeForKeyEvent
        };
    OS.InstallEventHandler(
        OS.GetControlEventTarget(webViewHandle),
        callback3Address,
        mask.length / 2,
        mask,
        webViewHandle,
        null);
    int[] mask1 =
        new int[] {
          OS.kEventClassControl,
          OS.kEventControlBoundsChanged,
          OS.kEventClassControl,
          OS.kEventControlVisibilityChanged,
          OS.kEventClassControl,
          OS.kEventControlOwningWindowChanged
        };
    OS.InstallEventHandler(
        OS.GetControlEventTarget(handle), callback3Address, mask1.length / 2, mask1, handle, null);
    int[] mask2 = new int[] {OS.kEventClassWindow, OS.kEventWindowBoundsChanged};
    int[] outRef = new int[1];
    OS.InstallEventHandler(
        OS.GetWindowEventTarget(window), callback3Address, mask2.length / 2, mask2, handle, outRef);
    windowBoundsHandler = outRef[0];
    if (Callback7 == null) {
      Callback7 = new Callback(this.getClass(), "eventProc7", 7);
    }
    int callback7Address = Callback7.getAddress();
    if (callback7Address == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    delegate = Cocoa.objc_msgSend(C_WebKitDelegate, S_alloc);
    delegate = Cocoa.objc_msgSend(delegate, S_initWithProc, callback7Address, webViewHandle);
    Cocoa.objc_msgSend(webView, S_setFrameLoadDelegate, delegate);
    Cocoa.objc_msgSend(webView, S_setResourceLoadDelegate, delegate);
    Cocoa.objc_msgSend(webView, S_setUIDelegate, delegate);
    Cocoa.objc_msgSend(
        notificationCenter,
        S_addObserver_selector_name_object,
        delegate,
        S_handleNotification,
        0,
        webView);
    Cocoa.objc_msgSend(webView, S_setPolicyDelegate, delegate);
    Cocoa.objc_msgSend(webView, S_setDownloadDelegate, delegate);
    if (!Initialized) {
      Initialized = true;
      int preferences = Cocoa.objc_msgSend(C_WebPreferences, S_standardPreferences);
      Cocoa.objc_msgSend(preferences, S_setJavaEnabled, 0);
    }
  }
}
