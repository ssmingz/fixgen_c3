class PlaceHold {
  static void registerAccessible(Accessible accessible) {
    int widget = accessible.getControlHandle();
    int widgetType = OS.G_OBJECT_TYPE(widget);
    int registry = ATK.atk_get_default_registry();
    int factory = ATK.atk_registry_get_factory(registry, widgetType);
    if (ATK.ATK_IS_NO_OP_OBJECT_FACTORY(factory)) {
      return;
    }
    String name = FACTORY_TYPENAME + getTypeName(widgetType);
    byte[] factoryName = Converter.wcsToMbcs(null, name, true);
    if (OS.g_type_from_name(factoryName) == 0) {
      if (AccessibleObject.DEBUG) {
        AccessibleObject.print("-->New Factory=" + name);
      }
      GTypeInfo typeInfo = new GTypeInfo();
      typeInfo.base_init = GTypeInfo_base_init_factory.getAddress();
      typeInfo.class_size = ((short) (ATK.AtkObjectFactoryClass_sizeof()));
      typeInfo.instance_size = ((short) (ATK.AtkObjectFactory_sizeof()));
      int info = OS.g_malloc(sizeof);
      OS.memmove(info, typeInfo, sizeof);
      int swtFactoryType =
          OS.g_type_register_static(ATK.ATK_TYPE_OBJECT_FACTORY(), factoryName, info, 0);
      int parentType = ATK.atk_object_factory_get_accessible_type(factory);
      ATK.atk_registry_set_factory_type(registry, widgetType, swtFactoryType);
      Factories.put(new LONG(widgetType), new LONG(parentType));
    }
    Accessibles.put(new LONG(widget), accessible);
  }
}
