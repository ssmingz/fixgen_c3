class PlaceHold {
  private void setError(char type, String message) {
    org.eclipse.compare.contentmergeviewer.IMergeViewerContentProvider cp =
        getMergeContentProvider();
    if (cp instanceof org.eclipse.compare.internal.MergeViewerContentProvider) {
      org.eclipse.compare.internal.MergeViewerContentProvider mcp =
          ((org.eclipse.compare.internal.MergeViewerContentProvider) (cp));
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
