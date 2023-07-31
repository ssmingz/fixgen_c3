class CoolBar {
  public CoolBar(Composite parent, int style) {
    super(parent, checkStyle(style));
    if ((style & SWT.VERTICAL) != 0) {
      this.style |= SWT.VERTICAL;
      hoverCursor = new Cursor(display, SWT.CURSOR_SIZENS);
    } else {
      this.style |= SWT.HORIZONTAL;
      hoverCursor = new Cursor(display, SWT.CURSOR_SIZEWE);
    }
    dragCursor = new Cursor(display, SWT.CURSOR_SIZEALL);
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                onDispose(event);
                break;
              case SWT.DragDetect:
                onDragDetect(event);
                break;
              case SWT.MouseDown:
                onMouseDown(event);
                break;
              case SWT.MouseExit:
                onMouseExit();
                break;
              case SWT.MouseMove:
                onMouseMove(event);
                break;
              case SWT.MouseUp:
                onMouseUp(event);
                break;
              case SWT.MouseDoubleClick:
                onMouseDoubleClick(event);
                break;
              case SWT.Paint:
                onPaint(event);
                break;
              case SWT.Resize:
                onResize();
                break;
            }
          }
        };
    int[] events =
        new int[] {
          SWT.Dispose,
          SWT.DragDetect,
          SWT.MouseDown,
          SWT.MouseExit,
          SWT.MouseMove,
          SWT.MouseUp,
          SWT.MouseDoubleClick,
          SWT.Paint,
          SWT.Resize
        };
    for (int i = 0; i < events.length; i++) {
      addListener(events[i], listener);
    }
  }
}
