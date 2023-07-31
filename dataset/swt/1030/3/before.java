class PlaceHold {
  TreeItem2[] computeAvailableDescendents() {
    if (!isExpanded) {
      return new TreeItem2[] {this};
    }
    int childCount = items.length;
    TreeItem2[][] childResults = new TreeItem2[childCount][];
    int count = 1;
    for (int i = 0; i < childCount; i++) {
      childResults[i] = items[i].computeAvailableDescendents();
      count += childResults[i].length;
    }
    TreeItem2[] result = new TreeItem2[count];
    int index = 0;
    result[index++] = this;
    for (int i = 0; i < childCount; i++) {
      System.arraycopy(childResults[i], 0, result, index, childResults[i].length);
      index += childResults[i].length;
    }
    return result;
  }
}
