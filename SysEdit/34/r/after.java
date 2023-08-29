class PlaceHold {
  protected Control createDialogArea(Composite parent2) {
    Composite parent = (Composite) super.createDialogArea(parent2);
    Control c = fCompareEditorInput.createContents(parent);
    c.setLayoutData(new GridData(GridData.FILL_BOTH));
    IPreferenceStore store = fCompareEditorInput.getCompareConfiguration().getPreferenceStore();
    if (store != null) {
      if (store.getBoolean(ComparePreferencePage.SHOW_MORE_INFO)) {
        statusLabel = new Label(parent, SWT.NONE);
        statusLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      }
    }
    Shell shell = c.getShell();
    shell.setText(fCompareEditorInput.getTitle());
    shell.setImage(fCompareEditorInput.getTitleImage());
    applyDialogFont(parent);
    return parent;
  }
}
