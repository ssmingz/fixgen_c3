class PlaceHold {
  private void createDropWidget(Composite parent) {
    parent.setLayout(new FormLayout());
    Combo combo = new Combo(parent, SWT.READ_ONLY);
    combo.setItems(
        new String[] {
          "Toggle Button",
          "Radio Button",
          "Checkbox",
          "Canvas",
          "Label",
          "List",
          "Table",
          "Tree",
          "Text"
        });
    combo.select(LABEL);
    dropControlType = combo.getSelectionIndex();
    dropControl = createWidget(dropControlType, parent, "Drop Target");
    combo.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Object data = dropControl.getLayoutData();
            Composite parent = dropControl.getParent();
            dropControl.dispose();
            Combo c = ((Combo) (e.widget));
            dropControlType = c.getSelectionIndex();
            dropControl = createWidget(dropControlType, parent, "Drop Target");
            dropControl.setLayoutData(data);
            if (dropEnabled) {
              createDropTarget();
            }
            parent.layout();
          }
        });
    Button b = new Button(parent, SWT.CHECK);
    b.setText("DropTarget");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            dropEnabled = b.getSelection();
            if (dropEnabled) {
              createDropTarget();
            } else {
              if (dropTarget != null) {
                dropTarget.dispose();
              }
              dropTarget = null;
            }
          }
        });
    b.setSelection(true);
    dropEnabled = true;
    FormData data = new FormData();
    data.top = new FormAttachment(0, 10);
    data.bottom = new FormAttachment(combo, -10);
    data.left = new FormAttachment(0, 10);
    data.right = new FormAttachment(100, -10);
    dropControl.setLayoutData(data);
    data = new FormData();
    data.bottom = new FormAttachment(100, -10);
    data.left = new FormAttachment(0, 10);
    combo.setLayoutData(data);
    data = new FormData();
    data.bottom = new FormAttachment(100, -10);
    data.left = new FormAttachment(combo, 10);
    b.setLayoutData(data);
  }
}
