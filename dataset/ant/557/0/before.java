class PlaceHold {
  public boolean isFollowSymlinks() {
    if (isReference()) {
      return getRef(getProject()).isFollowSymlinks();
    } else {
      return followSymlinks;
    }
  }
}
