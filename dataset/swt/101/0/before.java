class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int controlProc = display.controlProc;
    int colorProc = display.colorProc;
    int[] mask1 =
        new int[] {
          OS.kEventClassControl,
          OS.kEventControlDraw,
          OS.kEventClassControl,
          OS.kEventControlHit,
          OS.kEventClassControl,
          OS.kEventControlHitTest,
          OS.kEventClassControl,
          OS.kEventControlTrack
        };
    int controlTarget = OS.GetControlEventTarget(handle);
    OS.InstallEventHandler(controlTarget, controlProc, mask1.length / 2, mask1, handle, null);
    int[] mask2 =
        new int[] {
          OS.kEventClassControl,
          OS.kEventControlDraw,
          OS.kEventClassControl,
          OS.kEventControlHitTest,
          OS.kEventClassControl,
          OS.kEventControlTrack
        };
    int accessibilityProc = display.accessibilityProc;
    int[] mask3 =
        new int[] {
          OS.kEventClassAccessibility,
          OS.kEventAccessibleGetAllAttributeNames,
          OS.kEventClassAccessibility,
          OS.kEventAccessibleGetNamedAttribute
        };
    if (iconHandle != 0) {
      controlTarget = OS.GetControlEventTarget(iconHandle);
      OS.InstallEventHandler(controlTarget, controlProc, mask2.length / 2, mask2, iconHandle, null);
      OS.SetControlColorProc(iconHandle, colorProc);
      OS.SetControlAction(iconHandle, actionProc);
      OS.InstallEventHandler(
          controlTarget, accessibilityProc, mask3.length / 2, mask3, iconHandle, null);
    }
    if (labelHandle != 0) {
      controlTarget = OS.GetControlEventTarget(labelHandle);
      OS.InstallEventHandler(
          controlTarget, controlProc, mask2.length / 2, mask2, labelHandle, null);
      OS.SetControlColorProc(labelHandle, colorProc);
      OS.SetControlAction(labelHandle, actionProc);
    }
    if ((style & SWT.SEPARATOR) == 0) {
      OS.SetControlAction(handle, actionProc);
    }
    int helpProc = display.helpProc;
    OS.HMInstallControlContentCallback(handle, helpProc);
    OS.SetControlColorProc(handle, colorProc);
  }
}
