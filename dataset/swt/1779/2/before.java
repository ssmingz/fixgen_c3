class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Main Window");
    shell.setLayout(new FillLayout());
    final Browser browser;
    try {
      browser = new Browser(shell, SWT.NONE);
    } catch (SWTError e) {
      System.out.println("Could not instantiate Browser: " + e.getMessage());
      return;
    }
    initialize(display, browser);
    shell.open();
    browser.setUrl("http://www.eclipse.org");
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
