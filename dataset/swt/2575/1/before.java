class PlaceHold {
  int kEventControlDraw(int nextHandler, int theEvent, int userData) {
    int[] theControl = new int[1];
    OS.GetEventParameter(
        theEvent, kEventParamDirectObject, typeControlRef, null, 4, null, theControl);
    int[] region = new int[1];
    OS.GetEventParameter(theEvent, kEventParamRgnHandle, typeQDRgnHandle, null, 4, null, region);
    int visibleRgn = getVisibleRegion(theControl[0]);
    int oldClip = OS.NewRgn();
    OS.GetClip(oldClip);
    OS.SectRgn(region[0], visibleRgn, visibleRgn);
    OS.SetClip(visibleRgn);
    drawWidget(theControl[0]);
    int result = OS.CallNextEventHandler(nextHandler, theEvent);
    OS.SetClip(oldClip);
    OS.DisposeRgn(visibleRgn);
    OS.DisposeRgn(oldClip);
    return result;
  }
}
