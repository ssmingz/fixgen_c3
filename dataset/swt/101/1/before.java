class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int controlProc = display.controlProc;
    int[] mask =
        new int[] {
          OS.kEventClassControl,
          OS.kEventControlDraw,
          OS.kEventClassControl,
          OS.kEventControlSetFocusPart,
          OS.kEventClassControl,
          OS.kEventControlTrack
        };
    int controlTarget = OS.GetControlEventTarget(textHandle);
    OS.InstallEventHandler(controlTarget, controlProc, mask.length / 2, mask, handle, null);
    controlTarget = OS.GetControlEventTarget(buttonHandle);
    OS.InstallEventHandler(controlTarget, controlProc, mask.length / 2, mask, handle, null);
  }
}
