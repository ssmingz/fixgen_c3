class PlaceHold {
  public ZipExtraField[] getExtraFields(boolean includeUnparseable) {
    if (extraFields == null) {
      return (!includeUnparseable) || (unparseableExtra == null)
          ? new ZipExtraField[0]
          : new ZipExtraField[] {unparseableExtra};
    }
    List<ZipExtraField> result = new ArrayList<ZipExtraField>(extraFields.values());
    if (includeUnparseable && (unparseableExtra != null)) {
      result.add(unparseableExtra);
    }
    return result.toArray(new ZipExtraField[0]);
  }
}
