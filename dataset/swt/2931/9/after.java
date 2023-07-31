class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    final Image image = new Image(display, 1000, 1000);
    Color blue = display.getSystemColor(COLOR_BLUE);
    Color yellow = display.getSystemColor(COLOR_YELLOW);
    Color white = display.getSystemColor(COLOR_WHITE);
    GC gc = new GC(image);
    gc.setBackground(white);
    gc.setForeground(yellow);
    gc.fillGradientRectangle(0, 0, 1000, 1000, true);
    for (int i = -500; i < 1000; i += 10) {
      gc.setForeground(blue);
      gc.drawLine(i, 0, 500 + i, 1000);
      gc.drawLine(500 + i, 0, i, 1000);
    }
    gc.dispose();
    final Pattern pattern;
    try {
      pattern = new Pattern(display, image);
    } catch (SWTException e) {
      System.out.println(e.getMessage());
      display.dispose();
      return;
    }
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    Composite c = new Composite(shell, SWT.DOUBLE_BUFFERED);
    c.addListener(
        Paint,
        new Listener() {
          public void handleEvent(Event event) {
            Rectangle r = ((Composite) (event.widget)).getClientArea();
            GC gc = event.gc;
            gc.setBackgroundPattern(pattern);
            gc.fillOval(5, 5, r.width - 10, r.height - 10);
          }
        });
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    image.dispose();
    pattern.dispose();
    display.dispose();
  }
}
