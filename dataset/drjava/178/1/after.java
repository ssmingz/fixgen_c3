class PlaceHold {
  public List<OpenDefinitionsDocument> getNonProjectDocuments() {
    List<OpenDefinitionsDocument> allDocs = getDefinitionsDocuments();
    List<OpenDefinitionsDocument> projectDocs = new LinkedList<OpenDefinitionsDocument>();
    for (OpenDefinitionsDocument tempDoc : allDocs) {
      if (!tempDoc.isInProjectPath()) {
        projectDocs.add(tempDoc);
      }
    }
    return projectDocs;
  }
}
