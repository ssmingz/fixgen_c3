class PlaceHold {
  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.addListener(
        Paint,
        new Listener() {
          public void handleEvent(Event event) {
            int x = 20;
            int y = 20;
            int w = 120;
            int h = 60;
            GC gc = event.gc;
            gc.setForeground(display.getSystemColor(COLOR_BLUE));
            gc.setLineWidth(10);
            int[] caps = new int[] {SWT.CAP_FLAT, SWT.CAP_ROUND, SWT.CAP_SQUARE};
            for (int i = 0; i < caps.length; i++) {
              gc.setLineCap(caps[i]);
              gc.drawLine(x, y, x + w, y);
              y += 20;
            }
            int[] joins = new int[] {SWT.JOIN_BEVEL, SWT.JOIN_MITER, SWT.JOIN_ROUND};
            for (int i = 0; i < joins.length; i++) {
              gc.setLineJoin(joins[i]);
              gc.drawPolygon(new int[] {x, y, x + (w / 2), y + h, x + w, y});
              y += h + 20;
            }
          }
        });
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}
