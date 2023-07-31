class PlaceHold {
  protected void maybeSetResultPropertyValue(int result) {
    String res = Integer.toString(result);
    if (resultProperty != null) {
      getProject().setNewProperty(resultProperty, res);
    }
  }
}
