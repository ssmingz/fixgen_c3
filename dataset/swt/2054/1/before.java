class PlaceHold {
  private void createDragWidget(Composite parent) {
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
    dragControlType = combo.getSelectionIndex();
    dragControl = createWidget(dragControlType, parent, "Drag Source");
    combo.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Object data = dragControl.getLayoutData();
            Composite parent = dragControl.getParent();
            dragControl.dispose();
            Combo c = ((Combo) (e.widget));
            dragControlType = c.getSelectionIndex();
            dragControl = createWidget(dragControlType, parent, "Drag Source");
            dragControl.setLayoutData(data);
            if (dragEnabled) {
              createDragSource();
            }
            parent.layout();
          }
        });
    Button b = new Button(parent, SWT.CHECK);
    b.setText("DragSource");
    b.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            Button b = ((Button) (e.widget));
            dragEnabled = b.getSelection();
            if (dragEnabled) {
              createDragSource();
            } else {
              if (dragSource != null) {
                dragSource.dispose();
              }
              dragSource = null;
            }
          }
        });
    FormData data = new FormData();
    data.top = new FormAttachment(0, 10);
    data.bottom = new FormAttachment(combo, -10);
    data.left = new FormAttachment(0, 10);
    data.right = new FormAttachment(100, -10);
    dragControl.setLayoutData(data);
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
