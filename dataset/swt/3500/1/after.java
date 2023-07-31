class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    if (visible) {
      Display display = getDisplay();
      display.runDeferredEvents();
      sendEvent(Show);
      if (getItemCount() != 0) {
        int xDisplay = OS.XtDisplay(handle);
        if (xDisplay == 0) {
          return;
        }
        int xWindow = OS.XDefaultRootWindow(xDisplay);
        if (xWindow == 0) {
          return;
        }
        int[] rootX = new int[1];
        int[] rootY = new int[1];
        int[] unused = new int[1];
        int[] mask = new int[1];
        if (OS.XQueryPointer(xDisplay, xWindow, unused, unused, rootX, rootY, unused, unused, mask)
            == 0) {
          return;
        }
        if (!hasLocation) {
          rootX[0] += 1;
          rootY[0] += 1;
          int[] argList = new int[] {OS.XmNx, rootX[0], OS.XmNy, rootY[0]};
          OS.XtSetValues(handle, argList, argList.length / 2);
        }
        OS.XUngrabPointer(xDisplay, CurrentTime);
        OS.XtManageChild(handle);
        int flags = (OS.Button1Mask | OS.Button2Mask) | OS.Button3Mask;
        if ((mask[0] & flags) == 0) {
          OS._XmSetMenuTraversal(handle, true);
        }
      } else {
        sendEvent(Hide);
      }
    } else {
      OS.XtUnmanageChild(handle);
    }
  }
}
