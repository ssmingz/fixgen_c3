class PlaceHold {
  public String getDisplayFullPath(int index) {
    OpenDefinitionsDocument doc = getDefinitionsDocuments().get(index);
    if (doc == null) {
      throw new RuntimeException("Document not found with index " + index);
    }
    return GlobalModelNaming.getDisplayFullPath(doc);
  }
}
