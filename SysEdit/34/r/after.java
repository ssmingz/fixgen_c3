class PlaceHold {
  protected org.eclipse.compare.internal.Control createDialogArea(Composite parent2) {
    Composite parent = ((Composite) (super.createDialogArea(parent2)));
    Control c = fCompareEditorInput.createContents(parent);
    c.setLayoutData(new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.FILL_BOTH));
    org.eclipse.jface.preference.IPreferenceStore store =
        fCompareEditorInput.getCompareConfiguration().getPreferenceStore();
    if (store != null) {
      if (store.getBoolean(ComparePreferencePage.SHOW_MORE_INFO)) {
        statusLabel = new Label(parent, org.eclipse.swt.SWT.NONE);
        statusLabel.setLayoutData(
            new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.FILL_HORIZONTAL));
      }
    }
    Shell shell = c.getShell();
    shell.setText(fCompareEditorInput.getTitle());
    shell.setImage(fCompareEditorInput.getTitleImage());
    applyDialogFont(parent);
    return parent;
  }
}
