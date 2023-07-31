class JListSortNavigator {
  public JListSortNavigator() {
    super();
    _renderer = new DefaultListCellRenderer();
    _renderer.setOpaque(true);
    this.setCellRenderer(_renderer);
  }
}
