class PlaceHold {
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (!obj.getClass().equals(this.getClass()))) {
      return false;
    }
    PackageData packageData = ((PackageData) (obj));
    return super.equals(obj) && this.name.equals(packageData.name);
  }
}
