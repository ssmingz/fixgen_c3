class PlaceHold {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout());
    CCombo combo = new CCombo(shell, SWT.FLAT | SWT.BORDER);
    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    for (int i = 0; i < 5; i++) {
      combo.add("item" + i);
    }
    combo.setText("item0");
    combo.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            System.out.println("Item selected");
          }
        });
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
