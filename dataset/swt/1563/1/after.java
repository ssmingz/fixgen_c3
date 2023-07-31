class PlaceHold {
  public String open(Rectangle rect) {
    Point listSize = list.computeSize(rect.width, DEFAULT, false);
    Rectangle screenSize = shell.getDisplay().getBounds();
    int spaceBelow = (screenSize.height - (rect.y + rect.height)) - 30;
    int spaceAbove = rect.y - 30;
    int y = 0;
    if ((spaceAbove > spaceBelow) && (listSize.y > spaceBelow)) {
      if (listSize.y > spaceAbove) {
        listSize.y = spaceAbove;
      } else {
        listSize.y += 2;
      }
      y = rect.y - listSize.y;
    } else {
      if (listSize.y > spaceBelow) {
        listSize.y = spaceBelow;
      } else {
        listSize.y += 2;
      }
      y = rect.y + rect.height;
    }
    listSize.x = rect.width;
    if (listSize.x < minimumWidth) {
      listSize.x = minimumWidth;
    }
    int x = (rect.x + rect.width) - listSize.x;
    shell.setBounds(x, y, listSize.x, listSize.y);
    shell.open();
    list.setFocus();
    Display display = shell.getDisplay();
    while ((!shell.isDisposed()) && shell.isVisible()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    String result = null;
    if (!shell.isDisposed()) {
      String[] strings = list.getSelection();
      shell.dispose();
      if (strings.length != 0) {
        result = strings[0];
      }
    }
    return result;
  }
}
