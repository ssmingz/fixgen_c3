class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setBounds(10, 10, 200, 200);
    Text text = new Text(shell, SWT.MULTI);
    Rectangle clientArea = shell.getClientArea();
    text.setBounds(clientArea.x + 10, clientArea.y + 10, 150, 150);
    Font initialFont = text.getFont();
    FontData[] fontData = initialFont.getFontData();
    for (int i = 0; i < fontData.length; i++) {
      fontData[i].setHeight(24);
    }
    Font newFont = new Font(display, fontData);
    text.setFont(newFont);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    newFont.dispose();
    display.dispose();
  }
}
