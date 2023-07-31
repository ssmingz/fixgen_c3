class PlaceHold {
  protected void init() {
    super.init();
    initClasses();
    initColors();
    initFonts();
    if (!isEmbedded) {
      if (!Display.launched) {
        application.finishLaunching();
        Display.launched = true;
        Runtime.getRuntime()
            .addShutdownHook(
                new Thread() {
                  public void run() {
                    NSApplication.sharedApplication().terminate(null);
                  }
                });
      }
    }
    observerCallback = new Callback(this, "observerProc", 3);
    int observerProc = observerCallback.getAddress();
    if (observerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    int activities = OS.kCFRunLoopBeforeWaiting;
    runLoopObserver = OS.CFRunLoopObserverCreate(0, activities, true, 0, observerProc, 0);
    if (runLoopObserver == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.CFRunLoopAddObserver(OS.CFRunLoopGetCurrent(), runLoopObserver, OS.kCFRunLoopCommonModes());
    cursorSetCallback = new Callback(this, "cursorSetProc", 2);
    int cursorSetProc = cursorSetCallback.getAddress();
    if (cursorSetProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    int method = OS.class_getInstanceMethod(class_NSCursor, sel_set);
    if (method != 0) {
      oldCursorSetProc = OS.method_setImplementation(method, cursorSetProc);
    }
    timerDelegate = ((SWTWindowDelegate) (new SWTWindowDelegate().alloc().init()));
    settingsDelegate = ((SWTWindowDelegate) (new SWTWindowDelegate().alloc().init()));
    NSNotificationCenter defaultCenter = NSNotificationCenter.defaultCenter();
    defaultCenter.addObserver(
        settingsDelegate, sel_systemSettingsChanged_, NSSystemColorsDidChangeNotification, null);
    defaultCenter.addObserver(
        settingsDelegate,
        sel_systemSettingsChanged_,
        NSApplicationDidChangeScreenParametersNotification,
        null);
    NSTextView textView = ((NSTextView) (new NSTextView().alloc()));
    textView.init();
    markedAttributes = textView.markedTextAttributes();
    markedAttributes.retain();
    textView.release();
    isPainting = ((NSMutableArray) (new NSMutableArray().alloc()));
    isPainting = isPainting.initWithCapacity(12);
  }
}
