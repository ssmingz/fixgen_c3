class PlaceHold {
  void releaseDisplay() {
    OS.PtDestroyWidget(timerHandle);
    OS.free(ClassesPtr);
    OS.PtAppRemoveInput(app_context, input);
    OS.PtAppDeletePulse(app_context, pulse);
    if (timerIds != null) {
      for (int i = 0; i < timerIds.length; i++) {
        if (timerIds[i] != 0) {
          OS.PtDestroyWidget(timerIds[i]);
        }
      }
    }
    timerIds = null;
    timerList = null;
    timerProc = 0;
    timerCallback.dispose();
    timerCallback = null;
    windowCallback.dispose();
    windowCallback = null;
    drawCallback.dispose();
    drawCallback = null;
    workCallback.dispose();
    workCallback = null;
    inputCallback.dispose();
    inputCallback = null;
    hotkeyCallback.dispose();
    hotkeyCallback = null;
    if (nullImage != 0) {
      PhImage_t phImage = new PhImage_t();
      OS.memmove(phImage, nullImage, sizeof);
      phImage.flags = ((byte) (OS.Ph_RELEASE_IMAGE_ALL));
      OS.memmove(nullImage, phImage, sizeof);
      OS.PhReleaseImage(nullImage);
      OS.free(nullImage);
      nullImage = 0;
    }
    for (int i = 0; i < cursors.length; i++) {
      if (cursors[i] != null) {
        cursors[i].dispose();
      }
    }
    cursors = null;
    thread = null;
    data = null;
    keys = null;
    values = null;
  }
}
