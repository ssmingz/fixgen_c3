class PlaceHold {
  public void addDocument(ItemT doc, String path) {
    synchronized (_model) {
      GroupNode<ItemT> root = null;
      for (GroupNode<ItemT> r : _roots) {
        if (r.getFilter().accept(doc)) {
          root = r;
          break;
        }
      }
      if (root == null) {
        return;
      }
      StringTokenizer tok = new StringTokenizer(path, File.separator);
      StringBuffer pathSoFarBuf = new StringBuffer();
      InnerNode<?, ItemT> lastNode = root;
      while (tok.hasMoreTokens()) {
        String element = tok.nextToken();
        pathSoFarBuf.append(element).append('/');
        String pathSoFar = pathSoFarBuf.toString();
        InnerNode<?, ItemT> thisNode;
        if (!_path2node.containsKey(pathSoFar)) {
          thisNode = new FileNode<ItemT>(new File(pathSoFar));
          insertFolderSortedInto(thisNode, lastNode);
          this.expandPath(new TreePath(lastNode.getPath()));
          _path2node.put(pathSoFar, thisNode);
        } else {
          thisNode = _path2node.getValue(pathSoFar);
        }
        lastNode = thisNode;
      }
      LeafNode<ItemT> child = new LeafNode<ItemT>(doc);
      _doc2node.put(doc, child);
      insertNodeSortedInto(child, lastNode);
      this.expandPath(new TreePath(lastNode.getPath()));
      child.setUserObject(doc);
    }
  }
}
