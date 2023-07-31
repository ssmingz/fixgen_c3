class PlaceHold {
  public void createButtonSelected(SelectionEvent event) {
    if (shellCount >= shells.length) {
      Shell[] newShells = new Shell[shells.length + 4];
      System.arraycopy(shells, 0, newShells, 0, shells.length);
      shells = newShells;
    }
    int style = SWT.NONE;
    if (noTrimButton.getSelection()) {
      style |= SWT.NO_TRIM;
    }
    if (closeButton.getSelection()) {
      style |= SWT.CLOSE;
    }
    if (titleButton.getSelection()) {
      style |= SWT.TITLE;
    }
    if (minButton.getSelection()) {
      style |= SWT.MIN;
    }
    if (maxButton.getSelection()) {
      style |= SWT.MAX;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (resizeButton.getSelection()) {
      style |= SWT.RESIZE;
    }
    if (onTopButton.getSelection()) {
      style |= SWT.ON_TOP;
    }
    if (toolButton.getSelection()) {
      style |= SWT.TOOL;
    }
    if (modelessButton.getSelection()) {
      style |= SWT.MODELESS;
    }
    if (primaryModalButton.getSelection()) {
      style |= SWT.PRIMARY_MODAL;
    }
    if (applicationModalButton.getSelection()) {
      style |= SWT.APPLICATION_MODAL;
    }
    if (systemModalButton.getSelection()) {
      style |= SWT.SYSTEM_MODAL;
    }
    if (noParentButton.getSelection()) {
      shells[shellCount] = new Shell(style);
    } else {
      Shell shell = tabFolderPage.getShell();
      shells[shellCount] = new Shell(shell, style);
    }
    final Shell currentShell = shells[shellCount];
    Button button = new Button(currentShell, SWT.PUSH);
    button.setBounds(20, 20, 120, 30);
    Button closeButton = new Button(currentShell, SWT.PUSH);
    closeButton.setBounds(160, 20, 120, 30);
    closeButton.setText(ControlExample.getResourceString("Close"));
    closeButton.addListener(
        Selection,
        new Listener() {
          public void handleEvent(Event event) {
            currentShell.dispose();
          }
        });
    currentShell.setSize(300, 100);
    currentShell.setText(ControlExample.getResourceString("Title") + shellCount);
    hookListeners(currentShell);
    currentShell.open();
    shellCount++;
  }
}
