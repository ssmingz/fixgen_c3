class Browser {
  public Browser(Composite parent, int style) {
    super(parent, style);
    int[] args =
        new int[] {
          OS.Pt_ARG_ANCHOR_FLAGS,
          ((OS.Pt_TOP_ANCHORED_TOP | OS.Pt_BOTTOM_ANCHORED_BOTTOM) | OS.Pt_LEFT_ANCHORED_LEFT)
              | OS.Pt_RIGHT_ANCHORED_RIGHT,
          ((OS.Pt_TOP_ANCHORED_TOP | OS.Pt_BOTTOM_ANCHORED_BOTTOM) | OS.Pt_LEFT_ANCHORED_LEFT)
              | OS.Pt_RIGHT_ANCHORED_RIGHT,
          OS.Pt_ARG_FILL_COLOR,
          0xffffff,
          0
        };
    webHandle = OS.PtCreateWidget(OS.PtWebClient(), handle, args.length / 3, args);
    File netfront = new File("/usr/photon/bin/netfront");
    String name;
    String server;
    if (netfront.exists()
        || (((OS.QNX_MAJOR >= 6) && (OS.QNX_MINOR >= 3)) && (OS.QNX_MICRO >= 0))) {
      name = "NetfrontServer";
      server = "netfront";
    } else {
      name = "VoyagerServer-2";
      server = "vserver";
    }
    byte[] nameBuffer = Converter.wcsToMbcs(null, name, true);
    int namePtr = OS.malloc(nameBuffer.length);
    OS.memmove(namePtr, nameBuffer, nameBuffer.length);
    OS.PtSetResource(webHandle, Pt_ARG_CLIENT_NAME, namePtr, 0);
    OS.free(namePtr);
    byte[] serverBuffer = Converter.wcsToMbcs(null, server, true);
    int serverPtr = OS.malloc(serverBuffer.length);
    OS.memmove(serverPtr, serverBuffer, serverBuffer.length);
    OS.PtSetResource(webHandle, Pt_ARG_WEB_SERVER, serverPtr, 0);
    OS.free(serverPtr);
    if (callback == null) {
      callback = new Callback(this.getClass(), "webProc", 3, false);
    }
    int webProc = callback.getAddress();
    OS.PtAddCallback(webHandle, Pt_CB_WEB_COMPLETE, webProc, Pt_CB_WEB_COMPLETE);
    OS.PtAddCallback(webHandle, Pt_CB_WEB_START, webProc, Pt_CB_WEB_START);
    OS.PtAddCallback(webHandle, Pt_CB_WEB_STATUS, webProc, Pt_CB_WEB_STATUS);
    OS.PtAddCallback(webHandle, Pt_CB_WEB_URL, webProc, Pt_CB_WEB_URL);
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                onDispose();
                break;
              case SWT.FocusIn:
                onFocusGained(event);
                break;
            }
          }
        };
    int[] folderEvents = new int[] {SWT.Dispose, SWT.FocusIn};
    for (int i = 0; i < folderEvents.length; i++) {
      addListener(folderEvents[i], listener);
    }
    OS.PtRealizeWidget(webHandle);
  }
}
