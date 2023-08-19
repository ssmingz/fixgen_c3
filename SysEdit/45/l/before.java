class DiffTreeViewer {
  public DiffTreeViewer(Composite parent, CompareConfiguration configuration) {
    super(new Tree(parent, org.eclipse.swt.SWT.MULTI));
    initialize(configuration);
  }
}
