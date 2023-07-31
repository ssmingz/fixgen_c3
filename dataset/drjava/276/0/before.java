class PlaceHold {
  public ItemT removeDocument(ItemT doc) {
    synchronized (_model) {
      LeafNode<ItemT> toRemove = getNodeForDoc(doc);
      if (toRemove == null) {
        return null;
      }
      return removeNode(getNodeForDoc(doc));
    }
  }
}
