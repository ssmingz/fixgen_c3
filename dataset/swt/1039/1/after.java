class PlaceHold {
  private void createDragTypes(Composite parent) {
    parent.setLayout(new GridLayout());
    Button textButton = new Button(parent, SWT.CHECK);
    textButton.setText("Text Transfer");
    textButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDragTransfer(TextTransfer.getInstance());
            } else {
              removeDragTransfer(TextTransfer.getInstance());
            }
          }
        });
    Button b = new Button(parent, SWT.CHECK);
    b.setText("RTF Transfer");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDragTransfer(RTFTransfer.getInstance());
            } else {
              removeDragTransfer(RTFTransfer.getInstance());
            }
          }
        });
    b = new Button(parent, SWT.CHECK);
    b.setText("HTML Transfer");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDragTransfer(HTMLTransfer.getInstance());
            } else {
              removeDragTransfer(HTMLTransfer.getInstance());
            }
          }
        });
    b = new Button(parent, SWT.CHECK);
    b.setText("File Transfer");
    b.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDragTransfer(FileTransfer.getInstance());
            } else {
              removeDragTransfer(FileTransfer.getInstance());
            }
          }
        });
    b = new Button(parent, SWT.PUSH);
    b.setText("Select File(s)");
    b.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            FileDialog dialog = new FileDialog(fileList.getShell(), SWT.OPEN | SWT.MULTI);
            String result = dialog.open();
            if ((result != null) && (result.length() > 0)) {
              fileList.removeAll();
              String separator = System.getProperty("file.separator");
              String path = dialog.getFilterPath();
              String[] names = dialog.getFileNames();
              for (int i = 0; i < names.length; i++) {
                fileList.add((path + separator) + names[i]);
              }
            }
          }
        });
    fileList = new List(parent, (SWT.BORDER | SWT.H_SCROLL) | SWT.V_SCROLL);
    GridData data = new GridData();
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.BEGINNING;
    fileList.setLayoutData(data);
    textButton.setSelection(true);
    addDragTransfer(TextTransfer.getInstance());
  }
}
