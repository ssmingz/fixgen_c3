class PlaceHold {
  public Shell open(Display display) {
    createShell(display);
    createMenuBar();
    createStyledText();
    shell.open();
    return shell;
  }
}
