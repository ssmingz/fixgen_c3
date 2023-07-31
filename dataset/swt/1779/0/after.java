class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Media Player Example");
    shell.setLayout(new FillLayout());
    try {
      OleFrame frame = new OleFrame(shell, SWT.NONE);
      clientSite = new OleClientSite(frame, SWT.NONE, "WMPlayer.OCX");
      clientSite.doVerb(OLEIVERB_INPLACEACTIVATE);
      addFileMenu(frame);
    } catch (SWTError e) {
      System.out.println("Unable to open activeX control");
      display.dispose();
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
