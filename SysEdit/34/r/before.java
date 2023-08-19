class PlaceHold {
  protected org.eclipse.swt.widgets.Control createDialogArea(
      org.eclipse.swt.widgets.Composite parent2) {
    org.eclipse.swt.widgets.Composite parent =
        ((org.eclipse.swt.widgets.Composite) (super.createDialogArea(parent2)));
    org.eclipse.swt.widgets.Control c = fCompareEditorInput.createContents(parent);
    c.setLayoutData(new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.FILL_BOTH));
    org.eclipse.swt.widgets.Shell shell = c.getShell();
    shell.setText(fCompareEditorInput.getTitle());
    shell.setImage(fCompareEditorInput.getTitleImage());
    applyDialogFont(parent);
    return parent;
  }
}
