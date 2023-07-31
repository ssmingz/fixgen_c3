class CTabFolder {
  public CTabFolder(Composite parent, int style) {
    super(parent, checkStyle(style));
    int style2 = super.getStyle();
    oldFont = getFont();
    onBottom = (style2 & SWT.BOTTOM) != 0;
    showClose = (style2 & SWT.CLOSE) != 0;
    single = (style2 & SWT.SINGLE) != 0;
    borderLeft = borderRight = ((style & SWT.BORDER) != 0) ? 1 : 0;
    borderTop = (onBottom) ? borderLeft : 0;
    borderBottom = (onBottom) ? 0 : borderLeft;
    highlight_header = ((style & SWT.FLAT) != 0) ? 1 : 3;
    highlight_margin = ((style & SWT.FLAT) != 0) ? 0 : 2;
    Display display = getDisplay();
    selectionForeground = display.getSystemColor(SELECTION_FOREGROUND);
    selectionBackground = display.getSystemColor(SELECTION_BACKGROUND);
    borderColor = display.getSystemColor(BORDER1_COLOR);
    initAccessible();
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                onDispose();
                break;
              case SWT.DragDetect:
                onDragDetect(event);
                break;
              case SWT.FocusIn:
                onFocus(event);
                break;
              case SWT.FocusOut:
                onFocus(event);
                break;
              case SWT.MouseDoubleClick:
                onMouseDoubleClick(event);
                break;
              case SWT.MouseDown:
                onMouse(event);
                break;
              case SWT.MouseExit:
                onMouse(event);
                break;
              case SWT.MouseHover:
                onMouseHover(event);
                break;
              case SWT.MouseMove:
                onMouse(event);
                break;
              case SWT.MouseUp:
                onMouse(event);
                break;
              case SWT.Paint:
                onPaint(event);
                break;
              case SWT.Resize:
                onResize();
                break;
              case SWT.Traverse:
                onTraverse(event);
                break;
            }
          }
        };
    int[] folderEvents =
        new int[] {
          SWT.Dispose,
          SWT.DragDetect,
          SWT.FocusIn,
          SWT.FocusOut,
          SWT.KeyDown,
          SWT.MouseDoubleClick,
          SWT.MouseDown,
          SWT.MouseExit,
          SWT.MouseHover,
          SWT.MouseMove,
          SWT.MouseUp,
          SWT.Paint,
          SWT.Resize,
          SWT.Traverse
        };
    for (int i = 0; i < folderEvents.length; i++) {
      addListener(folderEvents[i], listener);
    }
    toolTipListener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.MouseHover:
              case SWT.MouseMove:
                if (updateToolTip(event.x, event.y)) {
                  break;
                }
              case SWT.MouseExit:
              case SWT.MouseDown:
                hideToolTip();
                break;
            }
          }
        };
  }
}
