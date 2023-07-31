class PlaceHold {
  public boolean supportsNestedElement(String elementName) {
    return ((nestedCreators.containsKey(elementName.toLowerCase(Locale.US))
                || DynamicElement.class.isAssignableFrom(bean))
            || DynamicElementNS.class.isAssignableFrom(bean))
        || (addTypeMethods.size() != 0);
  }
}
