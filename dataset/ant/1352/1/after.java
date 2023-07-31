class PlaceHold {
  public Object postCreateComponent(Object component, BuildElement model) throws AntException {
    String typeId = model.getNamespaceAttributeValue(ANT_META_URI, "id");
    if (typeId != null) {
      dataService.setMutableDataValue(typeId, component);
    }
    return super.postCreateComponent(component, model);
  }
}
