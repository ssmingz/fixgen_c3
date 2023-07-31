class PlaceHold {
  private void createDropTypes(Composite parent) {
    parent.setLayout(new RowLayout(SWT.VERTICAL));
    Button b = new Button(parent, SWT.CHECK);
    b.setText("Text Transfer");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDropTransfer(TextTransfer.getInstance());
            } else {
              removeDropTransfer(TextTransfer.getInstance());
            }
          }
        });
    b = new Button(parent, SWT.CHECK);
    b.setText("RTF Transfer");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDropTransfer(RTFTransfer.getInstance());
            } else {
              removeDropTransfer(RTFTransfer.getInstance());
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
              addDropTransfer(HTMLTransfer.getInstance());
            } else {
              removeDropTransfer(HTMLTransfer.getInstance());
            }
          }
        });
    b = new Button(parent, SWT.CHECK);
    b.setText("File Transfer");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            if (b.getSelection()) {
              addDropTransfer(FileTransfer.getInstance());
            } else {
              removeDropTransfer(FileTransfer.getInstance());
            }
          }
        });
  }
}
