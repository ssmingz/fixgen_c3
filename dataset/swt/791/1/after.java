class CTabFolder2 {
  public CTabFolder2(Composite parent, int style) {
    super(parent, checkStyle(style));
    int style2 = super.getStyle();
    onBottom = (style2 & SWT.BOTTOM) != 0;
    showClose = (style2 & SWT.CLOSE) != 0;
    single = (style2 & SWT.SINGLE) != 0;
    borderLeft =
        borderRight =
            ((style & SWT.BORDER) != 0) ? (style2 & SWT.FLAT) != 0 ? 1 : 1 + HIGHLIGHT_MARGIN : 0;
    borderTop = (onBottom) ? borderLeft : 0;
    borderBottom = (onBottom) ? 0 : borderRight;
    Display display = getDisplay();
    selectionForeground = display.getSystemColor(SELECTION_FOREGROUND);
    selectionBackground = display.getSystemColor(SELECTION_BACKGROUND);
    borderColor1 = new Color(getDisplay(), borderInsideRGB);
    borderColor2 = new Color(getDisplay(), borderMiddleRGB);
    borderColor3 = new Color(getDisplay(), borderOutsideRGB);
    setForeground(display.getSystemColor(FOREGROUND));
    setBackground(display.getSystemColor(BACKGROUND));
    initAccessible();
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                onDispose();
                break;
              case SWT.FocusIn:
                onFocus(event);
                break;
              case SWT.FocusOut:
                onFocus(event);
                break;
              case SWT.MenuDetect:
                onMenu(event);
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
          SWT.FocusIn,
          SWT.FocusOut,
          SWT.KeyDown,
          SWT.MenuDetect,
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
  }
}
