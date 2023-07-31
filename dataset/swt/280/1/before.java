class PlaceHold {
  void createDisplay(DeviceData data) {
    if (OS.VERSION < 0x1050) {
      System.out.println(
          ((("***WARNING: SWT requires MacOS X version " + 10) + ".") + 5) + " or greater");
      System.out.println(
          (((("***WARNING: Detected: " + Integer.toHexString((OS.VERSION & 0xff00) >> 8)) + ".")
                      + Integer.toHexString((OS.VERSION & 0xf0) >> 4))
                  + ".")
              + Integer.toHexString(OS.VERSION & 0xf));
      error(ERROR_NOT_IMPLEMENTED);
    }
    NSThread nsthread = NSThread.currentThread();
    if (!NSThread.isMainThread()) {
      System.out.println(
          "***WARNING: Display must be created on main thread due to Cocoa restrictions.");
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    NSMutableDictionary dictionary = nsthread.threadDictionary();
    NSString key = NSString.stringWith("SWT_NSAutoreleasePool");
    NSNumber id = new NSNumber(dictionary.objectForKey(key));
    addPool(new NSAutoreleasePool(id.integerValue()));
    application = NSApplication.sharedApplication();
    isEmbedded = application.isRunning();
    NSString identifier = NSBundle.mainBundle().bundleIdentifier();
    if (identifier == null) {
      int[] psn = new int[2];
      if (OS.GetCurrentProcess(psn) == OS.noErr) {
        int pid = OS.getpid();
        int ptr = getAppName().UTF8String();
        if (ptr != 0) {
          OS.CPSSetProcessName(psn, ptr);
        }
        OS.TransformProcessType(psn, kProcessTransformToForegroundApplication);
        OS.SetFrontProcess(psn);
        ptr = OS.getenv(ascii("APP_ICON_" + pid));
        if (ptr != 0) {
          NSString path = NSString.stringWithUTF8String(ptr);
          NSImage image = ((NSImage) (new NSImage().alloc()));
          image = image.initByReferencingFile(path);
          dockImage = image;
          application.setApplicationIconImage(image);
        }
      }
    }
    String className = "SWTApplication";
    int cls;
    if ((cls = OS.objc_lookUpClass(className)) == 0) {
      Class clazz = getClass();
      applicationCallback2 = new Callback(clazz, "applicationProc", 2);
      int proc2 = applicationCallback2.getAddress();
      if (proc2 == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      applicationCallback3 = new Callback(clazz, "applicationProc", 3);
      int proc3 = applicationCallback3.getAddress();
      if (proc3 == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      applicationCallback6 = new Callback(clazz, "applicationProc", 6);
      int proc6 = applicationCallback6.getAddress();
      if (proc6 == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      cls = OS.objc_allocateClassPair(OS.object_getClass(application.id), className, 0);
      OS.class_addMethod(cls, sel_sendEvent_, proc3, "@:@");
      OS.class_addMethod(cls, sel_nextEventMatchingMask_untilDate_inMode_dequeue_, proc6, "@:i@@B");
      OS.class_addMethod(cls, sel_isRunning, proc2, "@:");
      OS.class_addMethod(cls, sel_finishLaunching, proc2, "@:");
      OS.objc_registerClassPair(cls);
    }
    applicationClass = OS.object_setClass(application.id, cls);
    className = "SWTApplicationDelegate";
    if (OS.objc_lookUpClass(className) == 0) {
      int appProc3 = applicationCallback3.getAddress();
      if (appProc3 == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      cls = OS.objc_allocateClassPair(class_NSObject, className, 0);
      OS.class_addMethod(cls, sel_applicationWillFinishLaunching_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_terminate_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_quitRequested_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_orderFrontStandardAboutPanel_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_hideOtherApplications_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_hide_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_unhideAllApplications_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_applicationDidBecomeActive_, appProc3, "@:@");
      OS.class_addMethod(cls, sel_applicationDidResignActive_, appProc3, "@:@");
      OS.objc_registerClassPair(cls);
    }
    if (!isEmbedded) {
      if (applicationDelegate == null) {
        applicationDelegate =
            ((SWTApplicationDelegate) (new SWTApplicationDelegate().alloc().init()));
        application.setDelegate(applicationDelegate);
      }
    }
    int[] bufferMode = new int[1];
    int[] bufferOptions = new int[1];
    OS.GetSystemUIMode(bufferMode, bufferOptions);
    systemUIMode = bufferMode[0];
    systemUIOptions = bufferOptions[0];
  }
}
