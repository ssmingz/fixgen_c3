class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    FontData data = display.getSystemFont().getFontData()[0];
    Font font = new Font(display, data.getName(), 96, SWT.BOLD | SWT.ITALIC);
    final Color green = display.getSystemColor(COLOR_GREEN);
    final Color blue = display.getSystemColor(COLOR_BLUE);
    final Path path;
    try {
      path = new Path(display);
      path.addString("SWT", 0, 0, font);
    } catch (SWTException e) {
      System.out.println(e.getMessage());
      display.dispose();
      return;
    }
    Shell shell = new Shell(display);
    shell.addListener(
        Paint,
        new Listener() {
          public void handleEvent(Event e) {
            GC gc = e.gc;
            gc.setBackground(green);
            gc.setForeground(blue);
            gc.fillPath(path);
            gc.drawPath(path);
          }
        });
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    path.dispose();
    font.dispose();
    display.dispose();
  }
}
