class PlaceHold {
  public Shell open(Display display) {
    createShell(display);
    createMenuBar();
    createToolBar();
    createStyledText();
    shell.setSize(500, 300);
    shell.open();
    return shell;
  }
}
