class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Image image = new Image(display, 16, 16);
    Color color = display.getSystemColor(COLOR_RED);
    GC gc = new GC(image);
    gc.setBackground(color);
    gc.fillRectangle(image.getBounds());
    gc.dispose();
    Shell shell = new Shell(display);
    Label label = new Label(shell, SWT.BORDER);
    Rectangle clientArea = shell.getClientArea();
    label.setLocation(clientArea.x, clientArea.y);
    label.setImage(image);
    label.pack();
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    image.dispose();
    display.dispose();
  }
}
