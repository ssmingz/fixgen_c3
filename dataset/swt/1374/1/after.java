class PlaceHold {
  void showToolTip(int x, int y) {
    if (toolTipShell == null) {
      toolTipShell = new Shell(getShell(), SWT.ON_TOP | SWT.TOOL);
      toolTipLabel = new Label(toolTipShell, SWT.CENTER);
      Display display = toolTipShell.getDisplay();
      toolTipLabel.setForeground(display.getSystemColor(COLOR_INFO_FOREGROUND));
      toolTipLabel.setBackground(display.getSystemColor(COLOR_INFO_BACKGROUND));
      for (int i = 0; i < toolTipEvents.length; i++) {
        addListener(toolTipEvents[i], toolTipListener);
      }
    }
    if (updateToolTip(x, y)) {
      toolTipShell.setVisible(true);
    } else {
      hideToolTip();
    }
  }
}
