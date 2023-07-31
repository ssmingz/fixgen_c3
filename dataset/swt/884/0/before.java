class PlaceHold {
  int XPointerMotion(int w, int client_data, int call_data, int continue_to_dispatch) {
    display.addMouseHoverTimeOut(handle);
    XMotionEvent xEvent = new XMotionEvent();
    OS.memmove(xEvent, call_data, sizeof);
    int[] argList = new int[] {OS.XmNx, 0, OS.XmNy, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    xEvent.window = OS.XtWindow(parent.handle);
    xEvent.x += argList[1];
    xEvent.y += argList[3];
    parent.sendMouseEvent(MouseMove, xEvent);
    return 0;
  }
}
