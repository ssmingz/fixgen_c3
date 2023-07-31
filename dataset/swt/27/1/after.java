class PlaceHold {
  void makeAvailable(TreeItem item) {
    TreeItem parentItem = item.parentItem;
    int parentAvailableIndex = parentItem.availableIndex;
    TreeItem[] parentAvailableDescendents = parentItem.computeAvailableDescendents();
    TreeItem[] newAvailableItems = new TreeItem[availableItems.length + 1];
    System.arraycopy(availableItems, 0, newAvailableItems, 0, parentAvailableIndex);
    System.arraycopy(
        parentAvailableDescendents,
        0,
        newAvailableItems,
        parentAvailableIndex,
        parentAvailableDescendents.length);
    int startIndex = (parentAvailableIndex + parentAvailableDescendents.length) - 1;
    System.arraycopy(
        availableItems,
        startIndex,
        newAvailableItems,
        parentAvailableIndex + parentAvailableDescendents.length,
        availableItems.length - startIndex);
    availableItems = newAvailableItems;
    for (int i = parentAvailableIndex; i < availableItems.length; i++) {
      availableItems[i].availableIndex = i;
    }
    updateVerticalBar();
    Rectangle bounds = item.getBounds();
    int rightX = bounds.x + bounds.width;
    updateHorizontalBar(rightX, rightX);
  }
}
