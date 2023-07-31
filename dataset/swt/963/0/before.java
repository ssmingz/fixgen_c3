class PlaceHold {
  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setBounds(10, 10, 200, 240);
    Table table = new Table(shell, SWT.NONE);
    table.setBounds(10, 10, 160, 160);
    final TableItem[] items = new TableItem[4];
    for (int i = 0; i < 4; i++) {
      new TableColumn(table, SWT.NONE).setWidth(40);
    }
    for (int i = 0; i < 4; i++) {
      items[i] = new TableItem(table, SWT.NONE);
      populateItem(items[i]);
    }
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Change");
    button.pack();
    button.setLocation(10, 180);
    button.addListener(
        Selection,
        new Listener() {
          public void handleEvent(Event event) {
            for (int i = 0; i < 4; i++) {
              populateItem(items[i]);
            }
          }
        });
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
