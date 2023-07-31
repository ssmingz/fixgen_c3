class PlaceHold {
  void createDisplay(DeviceData data) {
    Win32.OleInitialize(0);
    application = OS.gcnew_Application();
    if (application == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.Application_ShutdownMode(application, ShutdownMode_OnExplicitShutdown);
  }
}
