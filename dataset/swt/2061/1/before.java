class PlaceHold {
  public Shell open(Display display) {
    createShell(display);
    createMenuBar();
    createToolBar();
    createStyledText();
    shell.open();
    return shell;
  }
}
