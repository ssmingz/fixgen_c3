class PlaceHold {
  int marginLeft(boolean isSelected) {
    boolean hasImage = (getImage() != null) && (isSelected || parent.showUnselectedImage);
    return parent.simple ? hasImage ? LEFT_SIMPLE_IMAGE_MARGIN : LEFT_SIMPLE_MARGIN : LEFT_MARGIN;
  }
}
