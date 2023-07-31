class PlaceHold {
  void installListeners() {
    ScrollBar verticalBar = getVerticalBar();
    ScrollBar horizontalBar = getHorizontalBar();
    listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                handleDispose(event);
                break;
              case SWT.KeyDown:
                handleKeyDown(event);
                break;
              case SWT.KeyUp:
                handleKeyUp(event);
                break;
              case SWT.MouseDown:
                handleMouseDown(event);
                break;
              case SWT.MouseUp:
                handleMouseUp(event);
                break;
              case SWT.MouseMove:
                handleMouseMove(event);
                break;
              case SWT.Paint:
                handlePaint(event);
                break;
              case SWT.Resize:
                handleResize(event);
                break;
              case SWT.Traverse:
                handleTraverse(event);
                break;
            }
          }
        };
    addListener(Dispose, listener);
    addListener(KeyDown, listener);
    addListener(KeyUp, listener);
    addListener(MouseDown, listener);
    addListener(MouseUp, listener);
    addListener(MouseMove, listener);
    addListener(Paint, listener);
    addListener(Resize, listener);
    addListener(Traverse, listener);
    if (verticalBar != null) {
      verticalBar.addListener(
          Selection,
          new Listener() {
            public void handleEvent(Event event) {
              handleVerticalScroll(event);
            }
          });
    }
    if (horizontalBar != null) {
      horizontalBar.addListener(
          Selection,
          new Listener() {
            public void handleEvent(Event event) {
              handleHorizontalScroll(event);
            }
          });
    }
  }
}
