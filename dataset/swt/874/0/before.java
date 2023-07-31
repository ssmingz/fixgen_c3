class PlaceHold {
  int treeSelectionProc(int model, int path, int iter, int data) {
    Widget widget = WidgetTable.get(data);
    if (widget == null) {
      return 0;
    }
    return widget.treeSelectionProc(model, path, iter, treeSelection, treeSelectionLength++);
  }
}
