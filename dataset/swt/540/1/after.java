class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    Image image = null;
    final ToolTip tip = new ToolTip(shell, SWT.BALLOON | SWT.ICON_INFORMATION);
    tip.setMessage(
        "Here is a message for the user. When the message is too long it wraps. I should say"
            + " something cool but nothing comes to my mind.");
    Tray tray = display.getSystemTray();
    if (tray != null) {
      TrayItem item = new TrayItem(tray, SWT.NONE);
      image = display.getSystemImage(ICON_INFORMATION);
      item.setImage(image);
      tip.setText("Notification from a tray item");
      item.setToolTip(tip);
    } else {
      tip.setText("Notification from anywhere");
      tip.setLocation(400, 400);
    }
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Press for balloon tip");
    button.addListener(
        Selection,
        new Listener() {
          public void handleEvent(Event event) {
            tip.setVisible(true);
          }
        });
    Rectangle clientArea = shell.getClientArea();
    button.setLocation(clientArea.x, clientArea.y);
    button.pack();
    shell.setBounds(50, 50, 300, 200);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (image != null) {
      image.dispose();
    }
    display.dispose();
  }
}
