class PlaceHold {
  protected void maybeSetResultPropertyValue(int result) {
    String res = Integer.toString(result);
    if (resultProperty != null) {
      project.setNewProperty(resultProperty, res);
    }
  }
}
