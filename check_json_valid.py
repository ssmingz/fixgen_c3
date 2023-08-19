import json
import os

base = f"/Users/yumeng/Desktop/json"
#base = f"/Users/yumeng/JavaProjects/FixGen/out/json"
pro = "cobertura"
invalid_counter = 0
total_counter = 0
for group in os.listdir(f"{base}/{pro}"):
    load_json = f"{base}/{pro}/{group}"
    group_data = json.load(open(load_json, 'r'))
    for case_path, case_data in group_data.items():
        total_counter += 1
        valid = True
        for part in case_data:
            node_size = len(part['vertexes'])
            if node_size != len(part['vertex_label']) or node_size != len(part['attributes']) or node_size != len(part['attribute_label']):
                valid = False
                break
        if not valid:
            invalid_counter += 1
            print(f"[error]{case_path}")
        else:
            print(f"[ok]{case_path}")
print(f"invalid/total:{invalid_counter}/{total_counter}")
