class PlaceHold {
  public Object postCreateComponent(Object component, BuildElement model) throws AntException {
    String typeId = model.getAspectAttributeValue(ANT_ASPECT, "id");
    if (typeId != null) {
      dataService.setMutableDataValue(typeId, component);
    }
    return super.postCreateComponent(component, model);
  }
}
