class PlaceHold {
  public boolean isFollowSymlinks() {
    return isReference() ? getRef(getProject()).isFollowSymlinks() : followSymlinks;
  }
}
