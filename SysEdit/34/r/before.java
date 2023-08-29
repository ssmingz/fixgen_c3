class PlaceHold {
  protected Control createDialogArea(Composite parent2) {
    Composite parent = (Composite) super.createDialogArea(parent2);
    Control c = fCompareEditorInput.createContents(parent);
    c.setLayoutData(new GridData(GridData.FILL_BOTH));
    Shell shell = c.getShell();
    shell.setText(fCompareEditorInput.getTitle());
    shell.setImage(fCompareEditorInput.getTitleImage());
    applyDialogFont(parent);
    return parent;
  }
}
