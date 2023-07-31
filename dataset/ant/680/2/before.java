class PlaceHold {
  public boolean supportsNestedElement(String elementName) {
    return (nestedCreators.containsKey(elementName)
            || DynamicConfigurator.class.isAssignableFrom(bean))
        || (addTypeMethods.size() != 0);
  }
}
