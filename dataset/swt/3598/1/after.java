class PlaceHold {
  void releaseDisplay() {
    OS.GCHandle_Free(timerHandler);
    timerHandler = 0;
    if (timerHandles != null) {
      for (int i = 0; i < timerHandles.length; i++) {
        int timer = timerHandles[i];
        if (timer != 0) {
          OS.DispatcherTimer_Stop(timer);
          OS.GCHandle_Free(timer);
        }
      }
    }
    timerHandles = null;
    timerList = null;
    if (application != 0) {
      OS.Application_Shutdown(application);
      OS.GCHandle_Free(application);
    }
    application = 0;
    if (dispatcher != 0) {
      OS.GCHandle_Free(dispatcher);
    }
    dispatcher = 0;
    if (frame != 0) {
      OS.GCHandle_Free(frame);
    }
    frame = 0;
    if (jniRef != 0) {
      OS.DeleteGlobalRef(jniRef);
    }
    jniRef = 0;
    for (int i = 0; i < colors.length; i++) {
      if (colors[i] != null) {
        colors[i].dispose();
      }
    }
    colors = null;
    if (errorImage != null) {
      errorImage.dispose();
    }
    if (infoImage != null) {
      infoImage.dispose();
    }
    if (questionImage != null) {
      questionImage.dispose();
    }
    if (warningIcon != null) {
      warningIcon.dispose();
    }
    errorImage = infoImage = questionImage = warningIcon = null;
    for (int i = 0; i < cursors.length; i++) {
      if (cursors[i] != null) {
        cursors[i].dispose();
      }
    }
    cursors = null;
    if (customColors != 0) {
      OS.GCHandle_Free(customColors);
    }
    customColors = 0;
    thread = null;
    modalShells = null;
    data = null;
    keys = null;
    values = null;
    popups = null;
    mouseControl = null;
    shells = null;
    Win32.OleUninitialize();
  }
}
