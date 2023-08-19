class DiffTreeViewer {
  public DiffTreeViewer(Tree tree, CompareConfiguration configuration) {
    super(tree);
    initialize(configuration == null ? new CompareConfiguration() : configuration);
  }
}
