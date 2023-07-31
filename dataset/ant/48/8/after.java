class PlaceHold{
protected Attributes createAttributes(MetricsElement elem) {
    AttributesImpl impl = new AttributesImpl();
    int i = 0;
    String name = ATTRIBUTES[i++];
    impl.addAttribute("", name, name, "CDATA", elem.getName());
    Iterator metrics = elem.getMetrics();
    for (; metrics.hasNext(); i++) {
        String value = ((String) (metrics.next()));
        if (value.length() > 0) {
            name = ATTRIBUTES[i];
            impl.addAttribute("", name, name, "CDATA", value);
        }
    }
    return impl;
}
}