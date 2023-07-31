class PlaceHold {
  void open() {
    display = new Display();
    font = new Font(display, "Courier", 10, SWT.NORMAL);
    foregroundColor = display.getSystemColor(COLOR_BLACK);
    backgroundColor = display.getSystemColor(COLOR_WHITE);
    shell = new Shell(display);
    shell.setLayout(new FillLayout());
    shell.setText("Print Text");
    shell.setMaximized(true);
    text = new Text(shell, ((SWT.BORDER | SWT.MULTI) | SWT.V_SCROLL) | SWT.H_SCROLL);
    text.setFont(font);
    text.setForeground(foregroundColor);
    text.setBackground(backgroundColor);
    Menu menuBar = new Menu(shell, SWT.BAR);
    shell.setMenuBar(menuBar);
    MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
    item.setText("&File");
    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(fileMenu);
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("&Open...");
    item.setAccelerator(SWT.CTRL + 'O');
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            menuOpen();
          }
        });
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("Font...");
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            menuFont();
          }
        });
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("Foreground Color...");
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            menuForegroundColor();
          }
        });
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("Background Color...");
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            menuBackgroundColor();
          }
        });
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("&Print...");
    item.setAccelerator(SWT.CTRL + 'P');
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            menuPrint();
          }
        });
    new MenuItem(fileMenu, SWT.SEPARATOR);
    item = new MenuItem(fileMenu, SWT.PUSH);
    item.setText("E&xit");
    item.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            System.exit(0);
          }
        });
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    if (font != null) {
      font.dispose();
    }
    if (foregroundColor != null) {
      foregroundColor.dispose();
    }
    if (backgroundColor != null) {
      backgroundColor.dispose();
    }
  }
}
