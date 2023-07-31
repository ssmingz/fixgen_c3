class PlaceHold {
  id accessibilityAttributeValue(NSString attribute) {
    return accessibleParent.internal_accessibilityAttributeValue(attribute, childID);
  }
}
