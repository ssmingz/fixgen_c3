class PlaceHold {
  public int hashCode() {
    if (imageDataProvider != null) {
      return imageDataProvider.hashCode();
    } else if (imageFileNameProvider != null) {
      return imageFileNameProvider.hashCode();
    } else {
      return handle != null ? ((int) (handle.id)) : 0;
    }
  }
}
