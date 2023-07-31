class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (horizontalButton.getSelection()) {
      style |= SWT.H_SCROLL;
    }
    if (verticalButton.getSelection()) {
      style |= SWT.V_SCROLL;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (noBackgroundButton.getSelection()) {
      style |= SWT.NO_BACKGROUND;
    }
    if (noFocusButton.getSelection()) {
      style |= SWT.NO_FOCUS;
    }
    if (noMergePaintsButton.getSelection()) {
      style |= SWT.NO_MERGE_PAINTS;
    }
    if (noRedrawResizeButton.getSelection()) {
      style |= SWT.NO_REDRAW_RESIZE;
    }
    paintCount = 0;
    cx = 0;
    cy = 0;
    canvas = new Canvas(canvasGroup, style);
    canvas.addPaintListener(
        new PaintListener() {
          public void paintControl(PaintEvent e) {
            paintCount++;
            GC gc = e.gc;
            if (fillDamageButton.getSelection()) {
              Color color = e.display.getSystemColor(colors[paintCount % CanvasTab.colors.length]);
              gc.setBackground(color);
              gc.fillRectangle(e.x, e.y, e.width, e.height);
            }
            Point size = canvas.getSize();
            gc.drawArc(cx + 1, cy + 1, size.x - 2, size.y - 2, 0, 360);
            gc.drawRectangle(cx + ((size.x - 10) / 2), cy + ((size.y - 10) / 2), 10, 10);
          }
        });
    canvas.addControlListener(
        new ControlAdapter() {
          public void controlResized(ControlEvent event) {
            Point size = canvas.getSize();
            maxX = (size.x * 3) / 2;
            maxY = (size.y * 3) / 2;
            resizeScrollBars();
          }
        });
    ScrollBar bar = canvas.getHorizontalBar();
    if (bar != null) {
      bar.addSelectionListener(
          new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              scrollHorizontal(((ScrollBar) (event.widget)));
            }
          });
    }
    bar = canvas.getVerticalBar();
    if (bar != null) {
      bar.addSelectionListener(
          new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              scrollVertical(((ScrollBar) (event.widget)));
            }
          });
    }
  }
}
