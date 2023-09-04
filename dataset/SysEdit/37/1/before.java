class PlaceHold {
  private void setError(char type, String message) {
    IMergeViewerContentProvider cp = getMergeContentProvider();
    if (cp instanceof MergeViewerContentProvider) {
      MergeViewerContentProvider mcp = (MergeViewerContentProvider) cp;
      switch (type) {
        case 'A':
          mcp.setAncestorError(message);
          break;
        case 'L':
          mcp.setLeftError(message);
          break;
        case 'R':
          mcp.setRightError(message);
          break;
      }
    }
    fHasErrors = true;
  }
}
