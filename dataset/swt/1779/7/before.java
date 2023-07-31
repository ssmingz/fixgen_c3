class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setText("PowerPoint Example");
    shell.setLayout(new FillLayout());
    try {
      OleFrame frame = new OleFrame(shell, SWT.NONE);
      clientSite = new OleClientSite(frame, SWT.NONE, "PowerPoint.Slide");
      addFileMenu(frame);
    } catch (SWTError e) {
      System.out.println("Unable to open activeX control");
      return;
    }
    shell.setSize(800, 600);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
