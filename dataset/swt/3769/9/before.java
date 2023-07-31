class PlaceHold {
  int OnShowTooltip(int aXCoords, int aYCoords, int aTipText) {
    int length = XPCOM.nsCRT_strlen_PRUnichar(aTipText);
    char[] dest = new char[length];
    XPCOM.memmove(dest, aTipText, length * 2);
    String text = new String(dest);
    if ((tip != null) && (!tip.isDisposed())) {
      tip.dispose();
    }
    Display display = getDisplay();
    Shell parent = getShell();
    tip = new Shell(parent, SWT.ON_TOP);
    tip.setLayout(new FillLayout());
    Label label = new Label(tip, SWT.CENTER);
    label.setForeground(display.getSystemColor(COLOR_INFO_FOREGROUND));
    label.setBackground(display.getSystemColor(COLOR_INFO_BACKGROUND));
    label.setText(text);
    Point point = display.getCursorLocation();
    point.y += 21;
    tip.setLocation(point);
    tip.pack();
    tip.setVisible(true);
    return XPCOM.NS_OK;
  }
}
