class PlaceHold {
  public List<OpenDefinitionsDocument> getNonProjectDocuments() {
    List<OpenDefinitionsDocument> allDocs = getDefinitionsDocuments();
    List<OpenDefinitionsDocument> projectDocs = new LinkedList<OpenDefinitionsDocument>();
    for (OpenDefinitionsDocument tempDoc : allDocs) {
      if (!tempDoc.isProjectFile()) {
        projectDocs.add(tempDoc);
      }
    }
    return projectDocs;
  }
}
