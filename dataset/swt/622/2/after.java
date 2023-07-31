class PlaceHold {
  void selectNode(Node node) {
    ArrayList path = new ArrayList();
    do {
      path.add(node);
      node = node.getParentNode();
    } while (node != null);
    TreeItem[] items = nodesTree.getItems();
    Collections.reverse(path);
    path.remove(0);
    while (true) {
      TreeItem item = findItem(items, ((Node) (path.remove(0))));
      if (item == null) {
        return;
      }
      if (path.isEmpty()) {
        nodesTree.setSelection(item);
        selectChild(item);
        return;
      }
      items = item.getItems();
    }
  }
}
