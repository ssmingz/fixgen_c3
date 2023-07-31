class PlaceHold {
  boolean checkAdvancedGraphics() {
    if (advanceGraphicsInit) {
      return advanceGraphics;
    }
    advanceGraphicsInit = true;
    Display display = Display.getCurrent();
    try {
      Path path = new Path(display);
      path.dispose();
    } catch (SWTException e) {
      Shell shell = display.getActiveShell();
      Shell newShell = null;
      if (shell == null) {
        shell = newShell = new Shell(display);
      }
      MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
      dialog.setText(RESOURCE_BUNDLE.getString("Warning"));
      dialog.setMessage(RESOURCE_BUNDLE.getString("LibNotFound"));
      dialog.open();
      if (newShell != null) {
        newShell.dispose();
      }
      return false;
    }
    return advanceGraphics = true;
  }
}
