class PlaceHold {
  int createControlTemplate() {
    int template = OS.gcnew_ControlTemplate();
    int borderType = OS.Border_typeid();
    int borderNode = OS.gcnew_FrameworkElementFactory(borderType);
    int brushProperty = OS.Control_BorderBrushProperty();
    int brushBinding = OS.gcnew_TemplateBindingExtension(brushProperty);
    OS.FrameworkElementFactory_SetValue(borderNode, brushProperty, brushBinding);
    int thicknessProperty = OS.Control_BorderThicknessProperty();
    int thicknessBinding = OS.gcnew_TemplateBindingExtension(thicknessProperty);
    OS.FrameworkElementFactory_SetValue(borderNode, thicknessProperty, thicknessBinding);
    int scrollViewerType = OS.ScrollViewer_typeid();
    int scrollViewerName = createDotNetString(SCROLLVIEWER_PART_NAME, false);
    int scrollViewerNode = OS.gcnew_FrameworkElementFactory(scrollViewerType, scrollViewerName);
    int itemsPresenterType = OS.ItemsPresenter_typeid();
    int itemsPresenterNode = OS.gcnew_FrameworkElementFactory(itemsPresenterType);
    OS.FrameworkElementFactory_AppendChild(borderNode, scrollViewerNode);
    OS.FrameworkElementFactory_AppendChild(scrollViewerNode, itemsPresenterNode);
    int scrollStyle = createDotNetString(scrollViewerStyle, false);
    int stringReader = OS.gcnew_StringReader(scrollStyle);
    int xmlReader = OS.XmlReader_Create(stringReader);
    int xamlStyle = OS.XamlReader_Load(xmlReader);
    int styleProperty = OS.FrameworkElement_StyleProperty();
    OS.FrameworkElementFactory_SetValue(scrollViewerNode, styleProperty, xamlStyle);
    int columnsProperty = OS.GridViewRowPresenterBase_ColumnsProperty();
    OS.FrameworkElementFactory_SetValue(scrollViewerNode, columnsProperty, columns);
    OS.FrameworkTemplate_VisualTree(template, borderNode);
    OS.GCHandle_Free(brushProperty);
    OS.GCHandle_Free(thicknessProperty);
    OS.GCHandle_Free(brushBinding);
    OS.GCHandle_Free(thicknessBinding);
    OS.GCHandle_Free(scrollStyle);
    OS.GCHandle_Free(stringReader);
    OS.GCHandle_Free(xmlReader);
    OS.GCHandle_Free(styleProperty);
    OS.GCHandle_Free(columnsProperty);
    OS.GCHandle_Free(xamlStyle);
    OS.GCHandle_Free(scrollViewerType);
    OS.GCHandle_Free(scrollViewerName);
    OS.GCHandle_Free(scrollViewerNode);
    OS.GCHandle_Free(borderType);
    OS.GCHandle_Free(borderNode);
    OS.GCHandle_Free(itemsPresenterType);
    OS.GCHandle_Free(itemsPresenterNode);
    return template;
  }
}
