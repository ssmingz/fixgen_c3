class ToolTipHandler {
  public ToolTipHandler(Shell parent) {
    final Display display = parent.getDisplay();
    this.parentShell = parent;
    tipShell = new Shell(parent, SWT.ON_TOP);
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 2;
    gridLayout.marginWidth = 2;
    gridLayout.marginHeight = 2;
    tipShell.setLayout(gridLayout);
    tipShell.setBackground(display.getSystemColor(COLOR_INFO_BACKGROUND));
    tipLabelImage = new Label(tipShell, SWT.NONE);
    tipLabelImage.setForeground(display.getSystemColor(COLOR_INFO_FOREGROUND));
    tipLabelImage.setBackground(display.getSystemColor(COLOR_INFO_BACKGROUND));
    tipLabelImage.setLayoutData(
        new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER));
    tipLabelText = new Label(tipShell, SWT.NONE);
    tipLabelText.setForeground(display.getSystemColor(COLOR_INFO_FOREGROUND));
    tipLabelText.setBackground(display.getSystemColor(COLOR_INFO_BACKGROUND));
    tipLabelText.setLayoutData(
        new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER));
  }
}
