class PlaceHold {
  int createScrollView(int parentControlHandle, int style) {
    Display display = getDisplay();
    int pos = -1;
    if (OS.IsValidControlHandle(parentControlHandle)) {
    } else if (OS.IsValidWindowPtr(parentControlHandle)) {
      int[] root = new int[1];
      if (OS.CreateRootControl(parentControlHandle, root) == OS.kNoErr) {
        parentControlHandle = root[0];
      } else {
        OS.HIViewFindByID(OS.HIViewGetRoot(parentControlHandle), 0, root);
        parentControlHandle = root[0];
        pos = -1;
      }
    } else {
      System.out.println("createScrollView: shouldn't happen");
    }
    int controlHandle = MacUtil.createDrawingArea(parentControlHandle, pos, true, 0, 0, 0);
    if ((style & SWT.H_SCROLL) != 0) {
      int hs =
          MacUtil.newControl(
              controlHandle,
              ((short) (0)),
              ((short) (0)),
              ((short) (100)),
              kControlScrollBarLiveProc);
      OS.SetControlAction(hs, display.fControlActionProc);
      fHScrollBar = hs;
    }
    if ((style & SWT.V_SCROLL) != 0) {
      int vs =
          MacUtil.newControl(
              controlHandle,
              ((short) (0)),
              ((short) (0)),
              ((short) (100)),
              kControlScrollBarLiveProc);
      OS.SetControlAction(vs, display.fControlActionProc);
      fVScrollBar = vs;
    }
    return controlHandle;
  }
}
