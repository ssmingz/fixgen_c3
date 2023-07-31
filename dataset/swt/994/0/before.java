class PlaceHold {
  public id internal_accessibilityAttributeValue_forParameter(
      NSString attribute, id parameter, int childID) {
    if (attribute.isEqualToString(NSAccessibilityStringForRangeParameterizedAttribute)) {
      return getStringForRangeParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityRangeForLineParameterizedAttribute)) {
      return getRangeForLineParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityLineForIndexParameterizedAttribute)) {
      return getLineForIndexParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityBoundsForRangeParameterizedAttribute)) {
      return getBoundsForRangeParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityRangeForPositionParameterizedAttribute)) {
      return getRangeForPositionParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityAttributedStringForRangeParameterizedAttribute)) {
      return getAttributedStringForRangeParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityStyleRangeForIndexParameterizedAttribute)) {
      return getStyleRangeForIndexAttribute(parameter, childID);
    }
    if ((OS.VERSION >= 0x1060)
        && attribute.isEqualToString(NSAccessibilityCellForColumnAndRowParameterizedAttribute)) {
      return getCellForColumnAndRowParameter(parameter, childID);
    }
    return null;
  }
}
