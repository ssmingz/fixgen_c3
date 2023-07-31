class PlaceHold {
  void createStyleGroup() {
    super.createStyleGroup();
    separatorButton = new Button(styleGroup, SWT.CHECK);
    separatorButton.setText("SWT.SEPARATOR");
    wrapButton = new Button(styleGroup, SWT.CHECK);
    wrapButton.setText("SWT.WRAP");
    horizontalButton = new Button(styleGroup, SWT.RADIO);
    horizontalButton.setText("SWT.HORIZONTAL");
    verticalButton = new Button(styleGroup, SWT.RADIO);
    verticalButton.setText("SWT.VERTICAL");
    Group styleSubGroup = new Group(styleGroup, SWT.NONE);
    styleSubGroup.setLayout(new GridLayout());
    shadowInButton = new Button(styleSubGroup, SWT.RADIO);
    shadowInButton.setText("SWT.SHADOW_IN");
    shadowOutButton = new Button(styleSubGroup, SWT.RADIO);
    shadowOutButton.setText("SWT.SHADOW_OUT");
    borderButton = new Button(styleGroup, SWT.CHECK);
    borderButton.setText("SWT.BORDER");
    SelectionListener selectionListener =
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            if ((event.widget.getStyle() & SWT.RADIO) != 0) {
              if (!((Button) (event.widget)).getSelection()) {
                return;
              }
            }
            recreateExampleWidgets();
          }
        };
    shadowInButton.addSelectionListener(selectionListener);
    shadowOutButton.addSelectionListener(selectionListener);
  }
}
