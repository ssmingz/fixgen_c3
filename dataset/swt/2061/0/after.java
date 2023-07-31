class PlaceHold {
  public Shell open(Display display) {
    createShell(display);
    createMenuBar();
    createStyledText();
    shell.setSize(500, 400);
    shell.open();
    return shell;
  }
}
