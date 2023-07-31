class PlaceHold {
  int XStructureNotify(int w, int client_data, int call_data, int continue_to_dispatch) {
    XConfigureEvent xEvent = new XConfigureEvent();
    OS.memmove(xEvent, call_data, sizeof);
    switch (xEvent.type) {
      case OS.ReparentNotify:
        {
          if (reparented) {
            return 0;
          }
          reparented = true;
          short[] root_x = new short[1];
          short[] root_y = new short[1];
          OS.XtTranslateCoords(shellHandle, ((short) (0)), ((short) (0)), root_x, root_y);
          int[] argList = new int[] {OS.XmNwidth, 0, OS.XmNheight, 0};
          OS.XtGetValues(shellHandle, argList, argList.length / 2);
          xEvent.x = root_x[0];
          xEvent.y = root_y[0];
          xEvent.width = argList[1];
          xEvent.height = argList[3];
        }
      case OS.ConfigureNotify:
        if (!reparented) {
          return 0;
        }
        configured = false;
        if ((oldX != xEvent.x) || (oldY != xEvent.y)) {
          sendEvent(Move);
        }
        if ((oldWidth != xEvent.width) || (oldHeight != xEvent.height)) {
          int xEvent1 = OS.XtMalloc(sizeof);
          display.resizeWindow = xEvent.window;
          display.resizeWidth = xEvent.width;
          display.resizeHeight = xEvent.height;
          display.resizeCount = 0;
          int checkResizeProc = display.checkResizeProc;
          OS.XCheckIfEvent(xEvent.display, xEvent1, checkResizeProc, 0);
          if (display.resizeCount == 0) {
            sendEvent(Resize);
            if (layout != null) {
              layout(false);
            }
          }
          OS.XtFree(xEvent1);
        }
        if (xEvent.x != 0) {
          oldX = xEvent.x;
        }
        if (xEvent.y != 0) {
          oldY = xEvent.y;
        }
        oldWidth = xEvent.width;
        oldHeight = xEvent.height;
        return 0;
      case OS.UnmapNotify:
        int[] argList = new int[] {OS.XmNmappedWhenManaged, 0};
        OS.XtGetValues(shellHandle, argList, argList.length / 2);
        if (argList[1] != 0) {
          minimized = true;
          sendEvent(Iconify);
        }
        return 0;
      case OS.MapNotify:
        if (minimized) {
          minimized = false;
          sendEvent(Deiconify);
        }
        return 0;
    }
    return 0;
  }
}
