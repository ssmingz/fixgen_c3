class TableItem {
  public TableItem(Table parent, int style) {
    this(parent, style, checkNull(parent).getItemCount());
  }
}
