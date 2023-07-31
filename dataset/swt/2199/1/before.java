class TreeItem {
  public TreeItem(Tree parent, int swtStyle) {
    this(parent, swtStyle, checkNull(parent).getItemCount());
  }
}
