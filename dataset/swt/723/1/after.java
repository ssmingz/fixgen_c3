class PlaceHold {
  static DropTarget FindDropTarget(int theWindow, int theDrag) {
    Display display = Display.findDisplay(Thread.currentThread());
    if ((display == null) || display.isDisposed()) {
      return null;
    }
    Point mouse = new Point();
    OS.GetDragMouse(theDrag, mouse, null);
    int[] theRoot = new int[1];
    OS.GetRootControl(theWindow, theRoot);
    int[] theControl = new int[1];
    Rect rect = new Rect();
    OS.GetWindowBounds(theWindow, ((short) (kWindowStructureRgn)), rect);
    CGPoint inPoint = new CGPoint();
    inPoint.x = mouse.h - rect.left;
    inPoint.y = mouse.v - rect.top;
    int[] event = new int[1];
    OS.CreateEvent(0, kEventClassMouse, kEventMouseDown, 0.0, 0, event);
    OS.SetEventParameter(event[0], kEventParamWindowMouseLocation, typeHIPoint, sizeof, inPoint);
    OS.HIViewGetViewForMouseEvent(theRoot[0], event[0], theControl);
    OS.ReleaseEvent(event[0]);
    if (!OS.IsControlEnabled(theControl[0])) {
      return null;
    }
    Widget widget = display.findWidget(theControl[0]);
    if (widget == null) {
      return null;
    }
    return ((DropTarget) (widget.getData(DROP_TARGET_KEY)));
  }
}
