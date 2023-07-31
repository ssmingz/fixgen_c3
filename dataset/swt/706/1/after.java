class PlaceHold {
  boolean setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    if (resize) {
      int[] argList = new int[] {OS.XmNminWidth, 0, OS.XmNminHeight, 0};
      OS.XtGetValues(shellHandle, argList, argList.length / 2);
      width = Math.max(1, Math.max(argList[1], width - trimWidth()));
      height = Math.max(1, Math.max(argList[3], height - trimHeight()));
    }
    if (move && resize) {
      OS.XtConfigureWidget(shellHandle, x, y, width, height, 0);
    } else {
      if (move) {
        OS.XtMoveWidget(shellHandle, x, y);
      }
      if (resize) {
        OS.XtResizeWidget(shellHandle, width, height, 0);
      }
    }
    if (redrawWindow != 0) {
      int xDisplay = OS.XtDisplay(handle);
      OS.XResizeWindow(xDisplay, redrawWindow, width, height);
    }
    if (move && ((oldX != x) || (oldY != y))) {
      moved = true;
      oldX = x + trimLeft();
      oldY = y + trimTop();
      sendEvent(Move);
      if (isDisposed()) {
        return false;
      }
    }
    if (resize && ((width != oldWidth) || (height != oldHeight))) {
      resized = true;
      oldWidth = width;
      oldHeight = height;
      sendEvent(Resize);
      if (isDisposed()) {
        return false;
      }
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false);
      }
    }
    return move || resize;
  }
}
