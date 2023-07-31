class PlaceHold {
  public ItemT removeDocument(ItemT doc) {
    assert EventQueue.isDispatchThread();
    LeafNode<ItemT> toRemove = getNodeForDoc(doc);
    if (toRemove == null) {
      return null;
    }
    return removeNode(getNodeForDoc(doc));
  }
}
