class PlaceHold {
  @Override
  public int hashCode() {
    if (imageDataProvider != null) {
      return imageDataProvider.hashCode();
    } else if (imageFileNameProvider != null) {
      return imageFileNameProvider.hashCode();
    } else {
      return ((int) (handle));
    }
  }
}
