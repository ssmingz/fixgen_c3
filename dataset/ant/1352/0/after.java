class PlaceHold {
  public Object preCreateComponent(Object component, BuildElement model) throws AntException {
    String refId = model.getNamespaceAttributeValue(ANT_META_URI, "refid");
    if (refId != null) {
      if ((model.getAttributeNames().hasNext() || model.getNestedElements().hasNext())
          || (model.getText().length() != 0)) {
        throw new AspectException(
            (("Element <" + model.getType())
                    + "> is defined by reference and hence may not specify ")
                + "any attributes, nested elements or content",
            model.getLocation());
      }
      Object referredComponent = dataService.getDataValue(refId);
      if (referredComponent == null) {
        throw new AspectException(
            ("The given ant:refid value '" + refId) + "' is not defined", model.getLocation());
      }
      return referredComponent;
    }
    return component;
  }
}
