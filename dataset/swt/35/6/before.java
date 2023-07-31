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
    OS.GetWindowBounds(theWindow, ((short) (kWindowContentRgn)), rect);
    CGPoint inPoint = new CGPoint();
    inPoint.x = mouse.h - rect.left;
    inPoint.y = mouse.v - rect.top;
    OS.HIViewGetSubviewHit(theRoot[0], inPoint, true, theControl);
    if (!OS.IsControlEnabled(theControl[0])) {
      return null;
    }
    Widget widget = display.findWidget(theControl[0]);
    if (widget == null) {
      return null;
    }
    return ((DropTarget) (widget.getData(DROPTARGETID)));
  }
}
