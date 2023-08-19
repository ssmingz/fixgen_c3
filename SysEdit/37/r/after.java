class PlaceHold {
  private void setError(char type, String message) {
    IMergeViewerContentProvider cp = getMergeContentProvider();
    if (cp instanceof MergeViewerContentProvider) {
      MergeViewerContentProvider mcp = ((MergeViewerContentProvider) (cp));
      switch (type) {
        case ANCESTOR_CONTRIBUTOR:
          mcp.setAncestorError(message);
          break;
        case LEFT_CONTRIBUTOR:
          mcp.setLeftError(message);
          break;
        case RIGHT_CONTRIBUTOR:
          mcp.setRightError(message);
          break;
      }
    }
    fHasErrors = true;
  }
}
