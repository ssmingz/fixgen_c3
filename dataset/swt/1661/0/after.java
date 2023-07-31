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
    if (noMoveButton.getSelection()) {
      style |= SWT.NO_MOVE;
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
    if (sheetButton.getSelection()) {
      style |= SWT.SHEET;
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
      shells[shellCount] = new Shell(shell, style);
    }
    final Shell currentShell = shells[shellCount];
    currentShell.setBackgroundMode(INHERIT_DEFAULT);
    final Button button = new Button(currentShell, SWT.CHECK);
    button.setBounds(20, 20, 120, 30);
    button.setText(ControlExample.getResourceString("FullScreen"));
    button.addSelectionListener(
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            currentShell.setFullScreen(button.getSelection());
          }
        });
    Button close = new Button(currentShell, SWT.PUSH);
    close.setBounds(160, 20, 120, 30);
    close.setText(ControlExample.getResourceString("Close"));
    close.addListener(
        Selection,
        new Listener() {
          @Override
          public void handleEvent(Event event) {
            currentShell.dispose();
          }
        });
    currentShell.setSize(300, 100);
    currentShell.setText(ControlExample.getResourceString("Title") + shellCount);
    if (imageButton.getSelection()) {
      currentShell.setImage(instance.images[ControlExample.ciTarget]);
    }
    if (backgroundImageButton.getSelection()) {
      currentShell.setBackgroundImage(instance.images[ControlExample.ciBackground]);
    }
    hookListeners(currentShell);
    currentShell.open();
    shellCount++;
  }
}
