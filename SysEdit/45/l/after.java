class DiffTreeViewer {
  public DiffTreeViewer(Composite parent, CompareConfiguration configuration) {
    super(new Tree(parent, SWT.MULTI));
    initialize(configuration == null ? new CompareConfiguration() : configuration);
  }
}
